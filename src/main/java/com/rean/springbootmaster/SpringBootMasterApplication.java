package com.rean.springbootmaster;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootMasterApplication {
	public static void main(String[] args) {
		System.out.println("Program is running...");
		SpringApplication.run(SpringBootMasterApplication.class, args);
		System.out.println("Program is done...");
	}

	//@Value("${app.name}") runtime error: Could not resolve placeholder 'app.name' in value "${app.name}"
	//@Value("${app.name:Spring Boot Master}") // default value
	@Value("${spring.application.name}")
	private String appName;
	@PostConstruct
	public void init() {
		System.out.println("App name: " + appName);
	}
	@Bean
	CommandLineRunner commandLineRunner(
			@Value("${custom.property}") String customProperty,
			DataPropertyConfiguration dataPropertyConfiguration) {
		System.out.println("Custom property: " + customProperty);
		System.out.println("Data property: " + dataPropertyConfiguration.getAppName());
		System.out.println("Data property: " + dataPropertyConfiguration.getCustomProperty());
		return args -> {};
	}
}
