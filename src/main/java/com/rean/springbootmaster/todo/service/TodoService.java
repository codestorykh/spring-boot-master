package com.rean.springbootmaster.todo.service;

import com.rean.springbootmaster.todo.dto.todo.TodoRequest;
import com.rean.springbootmaster.todo.dto.todo.TodoResponse;

import java.util.List;

public interface TodoService {
    void addTodo(TodoRequest todoRequest);
    void updateTodo(TodoRequest todoRequest);
    void deleteTodo(Long id);
    TodoResponse getTodo(Long id);
    List<TodoResponse> getTodos();
}
