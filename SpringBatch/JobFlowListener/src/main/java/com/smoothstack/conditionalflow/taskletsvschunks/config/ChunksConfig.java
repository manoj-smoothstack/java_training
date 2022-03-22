package com.smoothstack.conditionalflow.taskletsvschunks.config;

import com.smoothstack.conditionalflow.taskletsvschunks.chunks.LineProcessor;
import com.smoothstack.conditionalflow.taskletsvschunks.chunks.LineReader;
import com.smoothstack.conditionalflow.taskletsvschunks.chunks.LinesWriter;
import com.smoothstack.conditionalflow.taskletsvschunks.model.Line;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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
public class ChunksConfig {

    @Autowired private JobBuilderFactory jobs;

    @Autowired private StepBuilderFactory steps;

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    @Bean
    public JobRepository jobRepository1() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource1());
        factory.setTransactionManager(transactionManager1());
        return factory.getObject();
    }

    @Bean
    public DataSource dataSource1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:repository.sqlite");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager1() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobLauncher jobLauncher1() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository1());
        return jobLauncher;
    }

    @Bean
    public ItemReader<Line> itemReader1() {
        return new LineReader();
    }

    @Bean
    public ItemProcessor<Line, Line> itemProcessor() {
        return new LineProcessor();
    }

    @Bean
    public ItemWriter<Line> itemWriter2() {
        return new LinesWriter();
    }

    @Bean
    protected Step processLines(ItemReader<Line> reader, ItemProcessor<Line, Line> processor, ItemWriter<Line> writer) {
        return steps.get("processLines").<Line, Line> chunk(2)
          .reader(reader)
          .processor(processor)
          .writer(writer)
          .build();
    }

    @Bean
    public Job job() {
        return jobs
          .get("chunksJob")
          .start(processLines(itemReader1(), itemProcessor(), itemWriter2()))
          .build();
    }

}
