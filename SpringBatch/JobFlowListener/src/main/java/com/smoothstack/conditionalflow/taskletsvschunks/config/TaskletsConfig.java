package com.smoothstack.conditionalflow.taskletsvschunks.config;

import com.smoothstack.conditionalflow.taskletsvschunks.tasklets.LinesProcessor;
import com.smoothstack.conditionalflow.taskletsvschunks.tasklets.LinesReader;
import com.smoothstack.conditionalflow.taskletsvschunks.tasklets.LinesWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class TaskletsConfig {

    @Autowired private JobBuilderFactory jobs;

    @Autowired private StepBuilderFactory steps;

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils1() {
        return new JobLauncherTestUtils();
    }

    @Bean
    public JobRepository jobRepository2() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource3());
        factory.setTransactionManager(transactionManager2());
        return factory.getObject();
    }

    @Bean
    public DataSource dataSource3() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:repository.sqlite");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager2() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobLauncher jobLauncher3() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository2());
        return jobLauncher;
    }

    @Bean
    public LinesReader linesReader() {
        return new LinesReader();
    }

    @Bean
    public LinesProcessor linesProcessor() {
        return new LinesProcessor();
    }

    @Bean
    public LinesWriter linesWriter() {
        return new LinesWriter();
    }

    @Bean
    protected Step readLines() {
        return steps
          .get("readLines")
          .tasklet(linesReader())
          .build();
    }

    @Bean
    protected Step processLines1() {
        return steps
          .get("processLines")
          .tasklet(linesProcessor())
          .build();
    }

    @Bean
    protected Step writeLines() {
        return steps
          .get("writeLines")
          .tasklet(linesWriter())
          .build();
    }

    @Bean
    public Job job1() {
        return jobs
          .get("taskletsJob")
          .start(readLines())
          .next(processLines1())
          .next(writeLines())
          .build();
    }

}
