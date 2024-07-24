package com.rean.springbootmaster;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@SpringBootApplication (This annotation is equivalent to using @SpringBootConfiguration, @EnableAutoConfiguration and @ComponentScan)
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class SpringBootMasterApplication {
	public static void main(String[] args) {
		var context =  SpringApplication.run(SpringBootMasterApplication.class, args);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			//System.out.println(beanDefinitionName);
		}
		System.out.println("Beans:" + beanDefinitionNames.length);
	}

	/**
	 * This method is used to create a bean of type CommandLineRunner
	 * @return
	 */
	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("CommandLineRunner running...");
		};
	}

	/**
	 * This method is used to create a bean of type String
	 * @return
	 */
	@Bean
	public String message() {
		return "Hello, Spring Boot!";
	}
	@Bean(name = "inputBeanName")
	public String beanName() {
		return "This is a bean name";
	}

	@Component
	public class TodoService {
		public Todo getTodo() {
			Todo todo = new Todo();
			todo.setId("1");
			todo.setName("Todo 1");
			todo.setDescription("This is a todo description");
			return todo;
		}
	}

	public static class Todo{
		private String id;
		private String name;
		private String description;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "Todo{" +
					"id='" + id + '\'' +
					", name='" + name + '\'' +
					", description='" + description + '\'' +
					'}';
		}
	}
	@Bean
	CommandLineRunner commandLineRunner1(String message,
										 @Qualifier("inputBeanName") String beanName,
										 TodoService todoService) {
		return args -> {
			System.out.println("Message: " + message);
			System.out.println("Bean Name: " + beanName);
			System.out.println(todoService.getTodo());
		};
	}
}
