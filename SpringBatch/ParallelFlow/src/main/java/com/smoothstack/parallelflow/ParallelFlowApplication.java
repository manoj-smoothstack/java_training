package com.smoothstack.parallelflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParallelFlowApplication {
    public static void main(String[] args) {
        System.exit( SpringApplication.exit( SpringApplication.run(ParallelFlowApplication.class, args)));
    }

}
