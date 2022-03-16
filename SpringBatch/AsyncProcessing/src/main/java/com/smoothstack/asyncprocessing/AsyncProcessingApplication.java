package com.smoothstack.asyncprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsyncProcessingApplication {
    public static void main(String[] args) {
        System.exit( SpringApplication.exit( SpringApplication.run(AsyncProcessingApplication.class, args)));
    }

}
