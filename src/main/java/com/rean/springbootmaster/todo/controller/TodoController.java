package com.rean.springbootmaster.todo.controller;

import com.rean.springbootmaster.todo.dto.todo.TodoRequest;
import com.rean.springbootmaster.todo.dto.todo.TodoResponse;
import com.rean.springbootmaster.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/todos",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = "application/json")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Object> addTodo(@RequestBody TodoRequest todoRequest) {
        todoService.addTodo(todoRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Object> updateTodo(@RequestBody TodoRequest todoRequest) {
        todoService.updateTodo(todoRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodos() {
        return ResponseEntity.ok(todoService.getTodos());
    }
}
