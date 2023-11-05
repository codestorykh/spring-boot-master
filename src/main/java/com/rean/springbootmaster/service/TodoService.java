package com.rean.springbootmaster.service;

import com.springcourse.dto.TodoRequest;

public interface TodoService {

    void create(TodoRequest todoRequest);
    void update(TodoRequest todoRequest);
}
