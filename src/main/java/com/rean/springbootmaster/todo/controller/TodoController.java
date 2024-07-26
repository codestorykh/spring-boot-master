package com.rean.springbootmaster.todo.controller;

import com.rean.springbootmaster.todo.dto.TodoRequest;
import com.rean.springbootmaster.todo.dto.TodoResponse;
import com.rean.springbootmaster.todo.service.TodoService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rean.springbootmaster.todo.constant.Constant.API_VERSION;

@RestController
@RequestMapping(API_VERSION + "/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public HttpEntity<TodoResponse> create(@RequestBody TodoRequest todoRequest){
        return ResponseEntity.ok(todoService.create(todoRequest));
    }

    @PutMapping("{id}")
    public HttpEntity<TodoResponse> update(@RequestBody TodoRequest todoRequest, @PathVariable Long id){
        return ResponseEntity.ok(todoService.update(todoRequest, id));
    }

    @DeleteMapping("{id}")
    public HttpEntity<Object> delete(@PathVariable Long id){
        todoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public HttpEntity<TodoResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(todoService.get(id));
    }

    @GetMapping
    public HttpEntity<TodoResponse> getAll(){
        return ResponseEntity.ok(todoService.getAll());
    }
}
