package com.rean.springbootmaster.todo.service.impl;

import com.rean.springbootmaster.todo.repository.TodoRepository;
import com.rean.springbootmaster.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service for business logic of todo
 */
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void addTodo() {

    }

    @Override
    public void updateTodo() {

    }

    @Override
    public void deleteTodo() {

    }

    @Override
    public void getTodo() {

    }

    @Override
    public void getTodos() {

    }
}
