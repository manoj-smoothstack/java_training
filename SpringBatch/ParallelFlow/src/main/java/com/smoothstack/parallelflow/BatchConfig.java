package com.smoothstack.parallelflow;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import tasklet.*;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private DataSource dataSource;

    public Step downloadStep() {
        return steps.get("downloadStep")
                .tasklet(new DownloadTasklet())
                .build();
    }

    public Step fileProcessStep() {
        return steps.get("fileProcessStep")
                .tasklet(new FileProcessTasklet())
                .build();
    }

    public Step bizStep3() {
        return steps.get("bizStep3")
                .tasklet(new BizTasklet3())
                .build();
    }

    public Step bizStep4() {
        return steps.get("bizStep4")
                .tasklet(new BizTasklet4())
                .build();
    }

    public Step cleanupStep() {
        return steps.get("cleanupStep")
                .tasklet(new CleanupTasklet())
                .build();
    }

    public Flow fileFlow() {
        return new FlowBuilder<SimpleFlow>("fileFlow")
                .start(downloadStep())
                .next(fileProcessStep())
                .build();
    }

    public Flow bizFlow1() {
        return new FlowBuilder<SimpleFlow>("bizFlow1")
                .start(bizStep3())
                .build();
    }

    public Flow bizFlow2() {
        return new FlowBuilder<SimpleFlow>("bizFlow2")
                .start(bizStep4())
                .build();
    }

    public Flow splitFlow() {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(new SimpleAsyncTaskExecutor())
                .add(fileFlow(), bizFlow1(), bizFlow2())
                .build();
    }

    @Bean
    public Job job1() {
        return jobs.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(splitFlow())
                .next(cleanupStep())
                .end()
                .build();
    }
}