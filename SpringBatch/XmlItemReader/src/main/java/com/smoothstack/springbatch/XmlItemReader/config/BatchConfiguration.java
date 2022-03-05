package com.smoothstack.springbatch.XmlItemReader.config;

import com.smoothstack.springbatch.XmlItemReader.listener.HwJobExecutionListener;
import com.smoothstack.springbatch.XmlItemReader.listener.HwStepExecutionListener;
import com.smoothstack.springbatch.XmlItemReader.model.Product;
import com.smoothstack.springbatch.XmlItemReader.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


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
    public StaxEventItemReader xmlItemReader(@Value( "#{jobParameters['xmlFile']}" ) FileSystemResource inputFile){
        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setResource(inputFile);
        reader.setFragmentRootElementName("product");
        // tell reader how to parse XML and which domain object to be mapped
        reader.setUnmarshaller(new Jaxb2Marshaller(){
            {
                setClassesToBeBound(Product.class);
            }
        });

        return reader;
    }


    @Bean
    public Step xmlFileStep(){
        return steps.get("xmlFileStep").
                        <Integer,Integer>chunk(3)
                 .reader(xmlItemReader( null ))
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job xmlFileJob(){
        return jobs.get("xmlFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(hwJobExecutionListener)
                .start(xmlFileStep())
                .build();
    }
}
