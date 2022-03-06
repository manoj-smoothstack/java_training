package com.smoothstack.springbatch.JdbcCursorItemReader.config;

import com.smoothstack.springbatch.JdbcCursorItemReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.JdbcCursorItemReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.JdbcCursorItemReader.model.Student;
import com.smoothstack.springbatch.JdbcCursorItemReader.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

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
    public JdbcCursorItemReader jdbcCursorItemReader(){
        JdbcCursorItemReader reader = new JdbcCursorItemReader();
        reader.setDataSource(this.dataSource);
        reader.setSql("select * from student");
        reader.setRowMapper(new BeanPropertyRowMapper(){
            {
                setMappedClass(Student.class);
            }
        });
        return reader;
    }

    @Bean
    public Step jdbcCursorStep(){
        return steps.get("xmlFileStep").
                        <Integer,Integer>chunk(3)
                 .reader(jdbcCursorItemReader())
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job jdbcCursorJob(){
        return jobs.get("xmlFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(jdbcCursorStep())
                .build();
    }
}
