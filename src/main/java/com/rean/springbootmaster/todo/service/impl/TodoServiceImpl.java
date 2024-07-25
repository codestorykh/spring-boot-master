package com.rean.springbootmaster.todo.service.impl;

import com.rean.springbootmaster.todo.dto.todo.TodoRequest;
import com.rean.springbootmaster.todo.dto.todo.TodoResponse;
import com.rean.springbootmaster.todo.model.Todo;
import com.rean.springbootmaster.todo.repository.TodoRepository;
import com.rean.springbootmaster.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * service for business logic of todo
 */
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void addTodo(TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.title());
        todo.setDescription(todoRequest.description());
        todoRepository.save(todo);
    }

    @Override
    public void updateTodo(TodoRequest todoRequest) {
        Optional<Todo> todo = todoRepository.findById(todoRequest.id());
        if (todo.isPresent()) {
            Todo todo1 = todo.get();
            todo1.setTitle(todoRequest.title());
            todo1.setDescription(todoRequest.description());
            todoRepository.save(todo1);
        }
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public TodoResponse getTodo(Long id) {
        return todoRepository.findById(id)
                .map(todo -> new TodoResponse(
                                todo.getId(),
                                todo.getTitle(),
                                todo.getDescription()))
                .orElse(null);
    }

    @Override
    public List<TodoResponse> getTodos() {
        return todoRepository.findAll()
                .stream()
                .map(todo -> new TodoResponse(
                                todo.getId(),
                                todo.getTitle(),
                                todo.getDescription()))
                .toList();
    }
}
