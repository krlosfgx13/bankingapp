package com.project.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
public class BankingApplication {
	public static void main(String[] args) {
		System.setProperty("spring.config.location", "classpath:/config/shared/");
		SpringApplication.run(BankingApplication.class, args);
	}
}
