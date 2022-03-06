package com.smoothstack.springbatch.skipinwriter;


import com.smoothstack.springbatch.skipinwriter.listener.ProductSkipListener;
import com.smoothstack.springbatch.skipinwriter.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
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
import processor.ProductProcessor;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public Step skipInWriterStep() {
        return steps.get("skipInWriterStep")
                .<Product, Product>chunk(3)
                .reader(reader(null))
                .processor(new ProductProcessor())
                .writer(flatFileItemWriter(null))
                .faultTolerant()
                // .skip(RuntimeException.class)
                // .skipLimit(10)
                //Skippolicy
                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .listener(new ProductSkipListener())
                .build();
    }

    @Bean
    public Job skipInWriterJob() {
        return jobs.get("skipInWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(skipInWriterStep())
                .build();
    }

}
