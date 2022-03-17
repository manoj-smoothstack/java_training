package com.smoothstack.controljobflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControlJobFlowApplication {
    public static void main(String[] args) {
        System.exit( SpringApplication.exit( SpringApplication.run(ControlJobFlowApplication.class, args)));
    }

}
