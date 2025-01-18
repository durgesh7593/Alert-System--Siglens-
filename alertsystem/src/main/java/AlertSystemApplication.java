package com.example.alertsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.alertsystem")
public class AlertSystemApplication {

	public static void main(String[] args) {
		// Starting the Spring Boot application
		SpringApplication.run(AlertSystemApplication.class, args);
	}
}
