package com.smoothstack.springbatch.FlatFileItemReader.config;

import com.smoothstack.springbatch.FlatFileItemReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.FlatFileItemReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.FlatFileItemReader.model.Product;
import com.smoothstack.springbatch.FlatFileItemReader.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

@EnableBatchProcessing
@org.springframework.context.annotation.Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private HwJobExecutionListener hwJobExecutionListener;

    @Autowired
    private HwStepExecutionListener hwStepExecutionListener;

    @StepScope
    @Bean
    public FlatFileItemReader flatFileItemReader(
            @Value("#{jobParameters['flatFile']}")
                    FileSystemResource inputFile) {
        FlatFileItemReader reader = new FlatFileItemReader();
        // step 1 let reader know where is the file
        reader.setResource(inputFile);

        //create the line Mapper
        reader.setLineMapper(
                new DefaultLineMapper<Product>() {
                    {
                        setLineTokenizer(new DelimitedLineTokenizer() {
                            {
                                setNames(new String[]{"prodId", "productName", "prodDesc", "price", "unit"});
                                setDelimiter("|");
                            }
                        });

                        setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
                            {
                                setTargetType(Product.class);
                            }
                        });
                    }
                }

        );
        //step 3 tell reader to skip the header
        reader.setLinesToSkip(1);
        return reader;
    }

    @StepScope
    @Bean
    public FlatFileItemReader fixedWidthFileItemReader(
            @Value( "#{jobParameters['fixedWidthFile']}" )
                    FileSystemResource inputFile ){
        FlatFileItemReader reader = new FlatFileItemReader();
        // step 1 let reader know where is the file
        reader.setResource( inputFile );

        //create the line Mapper
        reader.setLineMapper(
                new DefaultLineMapper<Product>(){
                    {
                        setLineTokenizer( new FixedLengthTokenizer() {
                            {
                                setNames( new String[]{"prodId","productName","productDesc","price","unit"});
                                setColumns(
                                        new Range(1,16),
                                        new Range(17,41),
                                        new Range(42,65),
                                        new Range(66, 73),
                                        new Range(74,80)

                                );
                            }
                        });

                        setFieldSetMapper( new BeanWrapperFieldSetMapper<Product>(){
                            {
                                setTargetType(Product.class);
                            }
                        });
                    }
                }

        );
        //step 3 tell reader to skip the header
        reader.setLinesToSkip(1);
        return reader;

    }

    @Bean
    public Step flatFileStep(){
        return steps.get("step").
                        <Integer,Integer>chunk(4)
                 .reader(flatFileItemReader( null ))
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Step fixedWidthFileStep(){
        return steps.get("step").
                        <Integer,Integer>chunk(1)
                .reader(fixedWidthFileItemReader( null ))
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job flatFileJob(){
        return jobs.get("flatFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(flatFileStep())
                .build();
    }

    @Bean
    public Job fixedWidthFileJob(){
        return jobs.get("fixedWidthFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(fixedWidthFileStep())
                .build();
    }
}
