package com.rean.springbootmaster.service;

import com.rean.springbootmaster.dto.TodoBatchRequest;
import com.rean.springbootmaster.dto.TodoBatchResponse;
import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;

import java.util.List;

public interface TodoService {

    TodoResponse create(TodoRequest todoRequest);
    void batchCreate(List<TodoBatchRequest> batchRequests);
    TodoResponse update(TodoRequest todoRequest, long id);
    void delete(Long id);
    TodoResponse get(Long id);
    List<TodoBatchResponse> getAll(Integer page, Integer size);
}
