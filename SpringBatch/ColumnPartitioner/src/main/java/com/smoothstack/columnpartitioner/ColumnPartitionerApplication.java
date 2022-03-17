package com.smoothstack.columnpartitioner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColumnPartitionerApplication {
    public static void main(String[] args) {
        System.exit( SpringApplication.exit( SpringApplication.run(ColumnPartitionerApplication.class, args)));
    }

}
