package com.smoothstack.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootThymeleafWebAppApplication {
	public static void main(String[] args) {
		java.security.Security.setProperty("jdk.tls.disabledAlgorithms",""); // for MS SQL Server
		SpringApplication.run(SpringbootThymeleafWebAppApplication.class, args);
	}
}
