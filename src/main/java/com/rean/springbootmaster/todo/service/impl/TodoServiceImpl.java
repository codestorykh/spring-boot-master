package com.rean.springbootmaster.todo.service.impl;

import com.rean.springbootmaster.todo.dto.TodoRequest;
import com.rean.springbootmaster.todo.dto.TodoResponse;
import com.rean.springbootmaster.todo.model.Todo;
import com.rean.springbootmaster.todo.repository.TodoRepository;
import com.rean.springbootmaster.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponse create(TodoRequest todoRequest) {
        Todo todo = convertTodoRequestToTodoEntity(todoRequest);
        todoRepository.save(todo);
        log.info("Todo created: {}", todo);
        return convertTodoToTodoResponse(todo);
    }

    @Override
    public TodoResponse update(TodoRequest todoRequest, Long id) {
        Optional<Todo> todoOptional = todoRepository.findFirstById(id);
        if(todoOptional.isEmpty()){
            log.error("Todo not found with id: {}", todoRequest.getId());
            return null;
        }
        Todo todo = todoOptional.get();
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setCompleted(todoRequest.isCompleted());
        todoRepository.save(todo);
        return convertTodoToTodoResponse(todo);
    }

    @Override
    public void delete(Long id) {
        todoRepository.findFirstById(id)
                .ifPresent(todo -> {
                    todoRepository.delete(todo);
                    log.info("Todo deleted: {}", todo);
                });
    }

    @Override
    public TodoResponse get(Long id) {
        return todoRepository.findFirstById(id)
                .map(this::convertTodoToTodoResponse)
                .orElse(null);
    }

    @Override
    public TodoResponse getAll() {
        return todoRepository.findAll().stream()
                .map(this::convertTodoToTodoResponse)
                .findAny()
                .orElse(null);
    }

    public Todo convertTodoRequestToTodoEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setCompleted(todoRequest.isCompleted());
        return todo;
    }

    public TodoResponse convertTodoToTodoResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .build();
    }
}
