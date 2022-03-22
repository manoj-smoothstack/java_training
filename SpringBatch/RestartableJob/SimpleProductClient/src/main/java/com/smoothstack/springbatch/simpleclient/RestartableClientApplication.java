package com.smoothstack.springbatch.simpleclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestartableClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestartableClientApplication.class, args);
    }
}
