package com.rean.springbootmaster.controller;

import com.rean.springbootmaster.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/todos",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = "application/json")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public void addTodo() {
        todoService.addTodo();
    }

    @PutMapping
    public void updateTodo() {
        todoService.updateTodo();
    }

    @DeleteMapping
    public void deleteTodo() {
        todoService.deleteTodo();
    }

    @GetMapping("/{id}")
    public void getTodo() {
        todoService.getTodo();
    }

    @GetMapping
    public void getTodos() {
        todoService.getTodos();
    }
}
