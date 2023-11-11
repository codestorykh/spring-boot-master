package com.rean.springbootmaster.service;

import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse create(TodoRequest todoRequest);
    TodoResponse update(TodoRequest todoRequest);
    void delete(Long id);
    TodoResponse get(Long id);
    List<TodoResponse> getAll(int page, int size);
}
