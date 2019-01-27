package com.incidents.springbootangularapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.incidents")
@EnableAutoConfiguration
@EntityScan("com.incidents")
@EnableMongoRepositories("com.incidents")
@ComponentScan("com.incidents")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

