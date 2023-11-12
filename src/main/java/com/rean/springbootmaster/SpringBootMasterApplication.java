package com.rean.springbootmaster;

import com.rean.springbootmaster.dto.TodoBatchRequest;
import com.rean.springbootmaster.dto.TodoBatchResponse;
import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.model.Todo;
import com.rean.springbootmaster.repository.TodoRepository;
import com.rean.springbootmaster.service.TodoService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootMasterApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootMasterApplication.class, args);
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

		//todoService.batchCreate(todoRequests);
//
//		List<TodoBatchResponse> todoResponses = todoService.getAll(0, 10);
//		System.out.println(todoResponses.size());
	}
}
