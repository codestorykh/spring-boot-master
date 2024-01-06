package com.rean.springbootmaster;


import com.rean.springbootmaster.dto.TodoBatchRequest;
import com.rean.springbootmaster.dto.TodoBatchResponse;
import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.model.Todo;
import com.rean.springbootmaster.repository.TodoRepository;
import com.rean.springbootmaster.service.TodoService;
import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootMasterApplication {


	public static void main(String[] args) {

//		SpringApplication.run(SpringBootMasterApplication.class, args);
		var context = SpringApplication.run(SpringBootMasterApplication.class, args);

//		System.out.println("Count Beans" + context.getBeanDefinitionCount());
//
//		String[] allBeanNames = context.getBeanDefinitionNames();
//		for(String beanName : allBeanNames) {
//			System.out.println(beanName);
//		}
	}


	private final TodoService todoService;
	private final TodoRepository todoRepository;


	public SpringBootMasterApplication(TodoService todoService, TodoRepository todoRepository) {
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}

	@PostConstruct
	void saveTodo() {
//		TodoRequest todoRequest = new TodoRequest(
//				0L, "Todo", "Todo Description");
//		TodoResponse todo = todoService.create(todoRequest);
//		System.out.println(todo.name());
//
//		TodoRequest todoRequest2 = new TodoRequest(
//				1L, "Todo2", "Todo Update");
//		todo = todoService.update(todoRequest2);
//		System.out.println(todo.name());
//
//		System.out.println(todoRepository.findAll());


		List<TodoBatchRequest> todoRequests = Arrays.asList(
				new TodoBatchRequest(0L, "Todo 1", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 2", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 3", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 4", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 5", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 6", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 7", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 8", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 9", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 10", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 11", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 12", "Todo 1 Description", "ACT"),
				new TodoBatchRequest(0L, "Todo 13", "Todo 1 Description", "ACT")
		);
  }
		//todoService.batchCreate(todoRequests);
//
//		List<TodoBatchResponse> todoResponses = todoService.getAll(0, 10);
//		System.out.println(todoResponses.size());

	void newInstance() {
		RestTemplate rest = new RestTemplate();
	}

	// @PostConstruct will start after project is run
	/*@PostConstruct
	void test() {
		System.out.println("Hello World");
		System.out.println(todoRepository.findAll());
	}*/


	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			System.out.println("Hello World");
			System.out.println(todoRepository.findAll());
    }
	}
    
}
