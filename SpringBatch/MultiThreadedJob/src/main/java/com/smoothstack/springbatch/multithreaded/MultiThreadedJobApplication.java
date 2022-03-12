package com.smoothstack.springbatch.multithreaded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiThreadedJobApplication {
    public static void main(String[] args) {
        System.exit( SpringApplication.exit( SpringApplication.run(MultiThreadedJobApplication.class, args)));
    }
}
