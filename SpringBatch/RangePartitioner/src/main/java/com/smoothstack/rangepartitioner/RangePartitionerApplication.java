package com.smoothstack.rangepartitioner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RangePartitionerApplication {
    public static void main(String[] args) {
        System.exit( SpringApplication.exit( SpringApplication.run(RangePartitionerApplication.class, args)));
    }
}
