package com.rean.springbootmaster.todo.controller.client;

import com.rean.springbootmaster.todo.dto.TodoRequest;
import com.rean.springbootmaster.todo.dto.TodoResponse;
import com.rean.springbootmaster.todo.provider.TodoClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/client/todos")
public class TodoRestController {

    private final TodoClientService todoClientService;

    public TodoRestController(TodoClientService todoClientService) {
        this.todoClientService = todoClientService;
    }


    @GetMapping
    public List<TodoResponse> getAll() {
        return todoClientService.getAllTodos();
    }

    @GetMapping("{id}")
    public TodoResponse getById(@PathVariable("id") long id) {
        return todoClientService.getTodoById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") long id) {
        todoClientService.deleteTodoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TodoRequest todoRequest) {
        todoClientService.create(todoRequest);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") long id,
                           @RequestBody TodoRequest todoRequest) {
        todoClientService.update(id, todoRequest);
    }


}