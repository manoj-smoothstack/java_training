package com.smoothstack.asyncprocessing;


import com.smoothstack.asyncprocessing.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import processor.ProductProcessor;

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

    @Bean
    @StepScope
    public FlatFileItemReader reader(
            @Value("#{jobParameters['fileInput']}") FileSystemResource inputFile
    ) {

        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(inputFile);
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper() {
            {
                setFieldSetMapper(new BeanWrapperFieldSetMapper() {
                    {
                        setTargetType(Product.class);
                    }
                });

                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{"productId", "prodName", "productDesc", "price", "unit"});
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
    public Step step0() {
        return steps.get("step0")
                .tasklet(new ConsoleTasklet())
                .build();
    }

    @Bean
    public Step step1() {
        return steps.get("step1")
                .<Product, Product>chunk(5)
                .reader(reader(null))
                .processor(new ProductProcessor())
                .writer(flatFileItemWriter(null))
                .faultTolerant()
                .skip(FlatFileParseException.class)
                .skipLimit(3)
                .build();
    }

    @Bean
    public AsyncItemProcessor asyncItemProcessor() {
        AsyncItemProcessor processor = new AsyncItemProcessor();
        processor.setDelegate(new ProductProcessor());
        processor.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return processor;
    }

    @Bean
    public AsyncItemWriter asyncItemWriter() {
        AsyncItemWriter writer = new AsyncItemWriter();
        writer.setDelegate(flatFileItemWriter(null));
        return writer;
    }

    @Bean
    public Step asyncStep() {
        return steps.get("asyncStep")
                .<Product, Product>chunk(5)
                .reader(reader(null))
                .processor(asyncItemProcessor())
                .writer(asyncItemWriter())
                .build();
    }

    @Bean
    public Job asyncJob() {
        return jobs.get("asyncJob")
                .incrementer(new RunIdIncrementer())
                .start(step0())
                .next(step1())
                .next(asyncStep())
                .build();
    }
}
