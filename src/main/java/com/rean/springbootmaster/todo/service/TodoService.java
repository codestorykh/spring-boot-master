package com.rean.springbootmaster.todo.service;

import com.rean.springbootmaster.todo.dto.TodoRequest;
import com.rean.springbootmaster.todo.dto.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse create(TodoRequest todoRequest);
    TodoResponse update(TodoRequest todoRequest, Long id);
    void delete(Long id);
    TodoResponse get(Long id);
    List<TodoResponse> getAll();

}
