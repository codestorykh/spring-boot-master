package com.rean.springbootmaster.service;


import com.rean.springbootmaster.dto.TodoRequest;

public interface TodoService {

    void create(TodoRequest todoRequest);
    void update(TodoRequest todoRequest);
}
