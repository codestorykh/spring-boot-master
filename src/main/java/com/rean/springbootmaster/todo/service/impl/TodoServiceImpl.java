package com.rean.springbootmaster.todo.service.impl;

import com.rean.springbootmaster.todo.dto.TodoRequest;
import com.rean.springbootmaster.todo.dto.TodoResponse;
import com.rean.springbootmaster.todo.exception.ResourceNotFoundException;
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
            log.error("Todo not found with id: {}", todoRequest.id());
            throw new ResourceNotFoundException("Todo not found with id: " + todoRequest.id());
        }
        Todo todo = todoOptional.get();
        todo.setTitle(todoRequest.title());
        todo.setDescription(todoRequest.description());
        todo.setCompleted(todoRequest.completed());
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
                .orElseThrow(() -> {
                    log.error("Todo not found with id: {}", id);
                    return new ResourceNotFoundException("Todo not found with id: " + id);
                });
    }

    @Override
    public TodoResponse getAll() {
        return todoRepository.findAll().stream()
                .map(this::convertTodoToTodoResponse)
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("No todos found"));
    }

    public Todo convertTodoRequestToTodoEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.title());
        todo.setDescription(todoRequest.description());
        todo.setCompleted(todoRequest.completed());
        return todo;
    }

    public TodoResponse convertTodoToTodoResponse(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted());

    }
}
