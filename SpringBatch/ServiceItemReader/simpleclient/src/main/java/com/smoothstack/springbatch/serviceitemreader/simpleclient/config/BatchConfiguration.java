package com.smoothstack.springbatch.serviceitemreader.simpleclient.config;

import com.smoothstack.springbatch.serviceitemreader.simpleclient.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.serviceitemreader.simpleclient.reader.ProductServiceAdapter;
import com.smoothstack.springbatch.serviceitemreader.simpleclient.writer.ConsoleItemWriter;
import com.smoothstack.springbatch.serviceitemreader.simpleclient.service.ProductService;
import com.smoothstack.springbatch.serviceitemreader.simpleclient.listener.HwJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
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

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductServiceAdapter productServiceAdapter;

    public Tasklet pointlessTasklet(){
        return (new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Pointless tasklet for demo purposes  " );
                return RepeatStatus.FINISHED;
            }
        });
    }

    @Bean
    public Step pointlessStep(){
        return steps.get("step1")
                .listener(hwStepExecutionListener)
                .tasklet(pointlessTasklet())
                .build();
    }

    @Bean
    public ItemReaderAdapter serviceItemReader(){
        ItemReaderAdapter reader = new ItemReaderAdapter();
        reader.setTargetObject(productServiceAdapter);
        reader.setTargetMethod("nextProduct");
        return reader;
    }

    @Bean
    public Step simpleClientStep(){
        return steps.get("simpleClientStep").
                <Integer,Integer>chunk(3)
                .reader(serviceItemReader())
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job simpleClientJob(){
        return jobs.get("simpleClientJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(pointlessStep()) // note start() and next()
                .next(simpleClientStep())
                .build();
    }
}
