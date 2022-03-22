package com.smoothstack.columnpartitioner;


import com.smoothstack.columnpartitioner.model.Product;
import com.smoothstack.columnpartitioner.reader.ColumnRangePartitioner;
import com.smoothstack.columnpartitioner.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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
    public JdbcPagingItemReader pagingItemReader(
            @Value("#{stepExecutionContext['minValue']}") Long minValue,
            @Value("#{stepExecutionContext['maxValue']}") Long maxValue
    ) {
        System.out.println("From " + minValue + "to " + maxValue);
        Map<String, Order> sortKey = new HashMap<>();
        sortKey.put("product_id", Order.ASCENDING);

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("product_id, prod_name, prod_desc, unit, price");
        queryProvider.setFromClause("from product");
        queryProvider.setWhereClause("where product_id >=" + minValue + " and product_id <" + maxValue);
        queryProvider.setSortKeys(sortKey);

        JdbcPagingItemReader reader = new JdbcPagingItemReader();
        reader.setDataSource(this.dataSource);
        reader.setQueryProvider(queryProvider);
        reader.setFetchSize(1000);

        reader.setRowMapper(new BeanPropertyRowMapper() {
            {
                setMappedClass(Product.class);
            }
        });

        return reader;
    }

    public ColumnRangePartitioner columnRangePartitioner() {
        ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
        columnRangePartitioner.setColumn("product_id");
        columnRangePartitioner.setDataSource(dataSource);
        columnRangePartitioner.setTable("product");
        return columnRangePartitioner;
    }

    public Step partitionStep() {
        return steps.get("partitionStep")
                .partitioner(slaveStep().getName(), columnRangePartitioner())
                .step(slaveStep())
                .gridSize(3)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }


    public Step slaveStep() {
        return steps.get("slaveStep")
                .<Product, Product>chunk(5)
                .reader(pagingItemReader(null, null))
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public Job columnPartitionJob() {
        return jobs.get("columnPartitionJob")
                .incrementer(new RunIdIncrementer())
                .start(partitionStep())
                .build();
    }


}
