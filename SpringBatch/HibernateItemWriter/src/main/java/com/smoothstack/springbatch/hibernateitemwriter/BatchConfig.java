package com.smoothstack.springbatch.hibernateitemwriter;

import com.smoothstack.springbatch.hibernateitemwriter.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import processor.ProductProcessor;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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
            @Value( "#{jobParameters['fileInput']}"  ) FileSystemResource inputFile
            ){

        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(inputFile);
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper(){
            {
                setFieldSetMapper(new BeanWrapperFieldSetMapper(){
                    {
                        setTargetType(Product.class);
                    }
                });

                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(new String[]{"productId","prodName","productDesc"  ,"price","unit"});
                        setDelimiter(",");
                    }
                });
            }
        });

        return reader;
    }

    public JpaItemWriter jpaItemWriter() {
        return new JpaItemWriterBuilder<Product>()
                .entityManagerFactory(entityManagerFactory().getObject())
                .build();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("ENTER CONFIG");
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.smoothstack.springbatch.hibernateitemwriter" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("StrongPassword1$");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/hb_student_tracker?createDatabaseIfNotExist=true");

        return dataSource;
    }

   @Bean
   public Step hibernateItemWriterStep(){
        return steps.get("hibernateItemWriterStep")
                .<Product,Product>chunk(2)
                .reader(reader(null))
                .processor(new ProductProcessor())
                .writer(jpaItemWriter())
                .build();
   }

   @Bean
   public Job hibernateItemWriterJob(){
        return jobs.get("hibernateItemWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(hibernateItemWriterStep())
                .build();
   }

}
