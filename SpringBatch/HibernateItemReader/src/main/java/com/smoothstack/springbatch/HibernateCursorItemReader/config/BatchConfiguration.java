package com.smoothstack.springbatch.HibernateCursorItemReader.config;

import com.smoothstack.springbatch.HibernateCursorItemReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.HibernateCursorItemReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.HibernateCursorItemReader.model.Student;
import com.smoothstack.springbatch.HibernateCursorItemReader.writer.ConsoleItemWriter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

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

    @Autowired
    private DataSource dataSource;

    @Bean
    public HibernateCursorItemReader hibernateCursorItemReader() {
        HibernateCursorItemReader reader = new HibernateCursorItemReader();
        reader.setQueryString("FROM Student");
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        reader.setSessionFactory(factory);
        System.out.println("ENTERED HIBERNATE!");
        return reader;

    }

    @Bean
    public Step hibernateCursorStep(){
        return steps.get("hibernateCursorStep").
                        <Integer,Integer>chunk(3)
                 .reader(hibernateCursorItemReader())
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job hibernateCursorJob(){
        return jobs.get("hibernateCursorJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(hibernateCursorStep())
                .build();
    }
}
