package com.smoothstack.springbatch.jobflowprocessor;


import com.smoothstack.springbatch.jobflowprocessor.model.Product;
import com.smoothstack.springbatch.jobflowprocessor.step.NotifierTasklet;
import com.smoothstack.springbatch.jobflowprocessor.step.ProductClassifier;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.smoothstack.springbatch.jobflowprocessor.step.ProductDecider.NOTIFY;
import static com.smoothstack.springbatch.jobflowprocessor.step.ProductDecider.QUIET;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private DataSource dataSource;

    @Bean
    @StepScope
    public FlatFileItemReader reader(
            @Value( "#{jobParameters['fileInput']}"  ) FileSystemResource inputFile
            ){

        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(inputFile);
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper(){
            {
                setFieldSetMapper(new BeanWrapperFieldSetMapper(){
                    {
                        setTargetType(Product.class);
                    }
                });

                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(new String[]{"productId","prodName","productDesc"  ,"price","unit"});
                        setDelimiter(",");
                    }
                });
            }
        });

        return reader;
    }

   @Bean
   @StepScope
   public FlatFileItemWriter flatFileItemWriter(
           @Value("#{jobParameters['fileOutput']}" )FileSystemResource outputFile
     ){
        FlatFileItemWriter writer = new FlatFileItemWriter();

        writer.setResource(outputFile);
        writer.setLineAggregator( new DelimitedLineAggregator(){
            {
                setDelimiter("|");
                setFieldExtractor(new BeanWrapperFieldExtractor(){
                    {
                        setNames(new String[]{"productId","prodName","productDesc","price","unit" });
                    }
                });
            }
        });

        // how to write the header
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("productID,productName,ProductDesc,price,unit");
            }
        });

        writer.setAppendAllowed(false);

        writer.setFooterCallback(new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.write(" The file is created at " + new SimpleDateFormat().format(new Date()));
            }
        });
        return writer;
   }

    @Bean
    @Qualifier("NotificationStep")
    public Step notificationStep(StepBuilderFactory sbf) {
        return sbf.get("Notify step")
                .tasklet(new NotifierTasklet())
                .build();
    }

    public Step jobFlowStep(StepBuilderFactory sbf) {
        return sbf.get("jobFlowStep")
                .<Product, Integer> chunk(1)
                .reader(reader(null))
                .processor(new ProductClassifier())
                .writer(new FlatFileItemWriter())
                .build();
    }

    @Bean
    @Qualifier("flowJob")
    public Job flowJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, @Qualifier("NotificationStep") Step notificationStep) {
        Step dataProviderStep = jobFlowStep(stepBuilderFactory);
        return jobBuilderFactory.get("flowJob")
                .start(dataProviderStep)
                .on(NOTIFY)
                .to(notificationStep)
                //.on(FAILED)
                //.to(notificationStep)
                .end()
                .build();
    }

}
