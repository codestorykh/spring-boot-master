package com.rean.springbootmaster;

import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.model.Todo;
import com.rean.springbootmaster.repository.TodoRepository;
import com.rean.springbootmaster.service.TodoService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		TodoRequest todoRequest = new TodoRequest(
				0L, "Todo", "Todo Description");
		TodoResponse todo = todoService.create(todoRequest);
		System.out.println(todo.name());

		TodoRequest todoRequest2 = new TodoRequest(
				1L, "Todo2", "Todo Update");
		todo = todoService.update(todoRequest2);
		System.out.println(todo.name());

		System.out.println(todoRepository.findAll());
	}
}
