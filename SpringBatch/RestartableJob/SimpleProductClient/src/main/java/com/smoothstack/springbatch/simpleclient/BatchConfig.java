package com.smoothstack.springbatch.simpleclient;


import com.smoothstack.springbatch.simpleclient.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
//import processor.ProductProcessor;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private DataSource dataSource;

    //@Autowired
    //ProductServiceAdapter adapter;

    /*
    public ItemReaderAdapter serviceAdapter() {
        ItemReaderAdapter readerAdapter = new ItemReaderAdapter();
        readerAdapter.setTargetObject(adapter);
        readerAdapter.setTargetMethod("nextProduct");
        return readerAdapter;
    }
     */

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
    public FlatFileItemWriter<Product> flatFileItemWriter(
            @Value("#{jobParameters['fileOutput']}") FileSystemResource outputFile
    ) {

        /*
        FlatFileItemWriter writer = new FlatFileItemWriter<Product>() {
            @Override
            public String doWrite(List<? extends Product> items) {
                for (Product p : items) {
                    if (p.getProductId() == 9)
                        throw new RuntimeException("Because ID is 9");
                }
                return super.doWrite(items);
            }
        };
         */

        FlatFileItemWriter writer = new FlatFileItemWriter();

        writer.setResource(outputFile);
        writer.setLineAggregator(new DelimitedLineAggregator() {
            {
                setDelimiter("|");
                setFieldExtractor(new BeanWrapperFieldExtractor() {
                    {
                        setNames(new String[]{"productId", "prodName", "productDesc", "price", "unit"});

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

        writer.setAppendAllowed(true);

        return writer;
    }

    @Bean
    public Step restartStep() {
        return steps.get("retryStep")
                .<Product, Product>chunk(3)
                .reader(reader(null))
                .writer(flatFileItemWriter(null))
                .build();
    }

    @Bean
    public Step step0() {
        return steps.get("step0")
                .tasklet(new ConsoleTasklet())
                .build();
    }
    @Bean
    public Job restartJob() {
        return jobs.get("retryJob")
                .start(step0())
                .next(restartStep())
                .build();
    }

}
