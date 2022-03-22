package com.smoothstack.springbatch.JsonItemReader.config;

import com.smoothstack.springbatch.JsonItemReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.JsonItemReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.JsonItemReader.model.Product;
import com.smoothstack.springbatch.JsonItemReader.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
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
    public JsonItemReader jsonItemReader(@Value( "#{jobParameters['jsonFile']}" ) FileSystemResource inputFile){
        JsonItemReader reader = new JsonItemReader(inputFile, new JacksonJsonObjectReader(Product.class));
        return reader;
    }

    @Bean
    public Step jsonFileStep(){
        return steps.get("jsonFileStep")
                .listener(hwStepExecutionListener)
                .<Integer,Integer>chunk(3)
                .reader(jsonItemReader( null ))
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job jsonFileJob(){
        return jobs.get("jsonFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(jsonFileStep())
                .build();
    }
}
