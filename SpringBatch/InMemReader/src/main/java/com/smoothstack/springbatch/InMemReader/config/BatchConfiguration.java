package com.smoothstack.springbatch.InMemReader.config;

import com.smoothstack.springbatch.InMemReader.reader.InMemReader;
import com.smoothstack.springbatch.InMemReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.InMemReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.InMemReader.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public InMemReader inMemReader(){
        return new InMemReader();
    }


    @Bean
    public Step inMemStep(){
        return steps.get("xmlFileStep").
                        <Integer,Integer>chunk(3)
                .reader(inMemReader())
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job inMemJob(){
        return jobs.get("inMemJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(inMemStep())
                .build();
    }
}
