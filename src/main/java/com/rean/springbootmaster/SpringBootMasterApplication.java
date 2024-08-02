package com.rean.springbootmaster;

import com.rean.springbootmaster.todo.model.Todo;
import com.rean.springbootmaster.todo.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootMasterApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMasterApplication.class, args);
	}

	@Autowired
	private TodoRepository todoRepository;

	@PostConstruct
	public void init() {
		List<Todo> todos = todoRepository.findAllByCompleted(true);

		Optional<Todo> todo = todoRepository.findFirstById(1);

		List<Todo> todosByTitle = todoRepository.findAllByTitleContaining("title");

		List<Todo> todosByDescription = todoRepository.findAllByDescriptionContaining("description");
	}
}
