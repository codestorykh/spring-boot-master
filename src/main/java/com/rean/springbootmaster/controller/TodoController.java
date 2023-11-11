package com.rean.springbootmaster.controller;

import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.model.Todo;
import com.rean.springbootmaster.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createOrUpdate(@RequestBody TodoRequest todo) {

        TodoResponse todoInstance = todoService.create(todo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(todoInstance.id()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateCompleted(@PathVariable Long id) {
        Todo todo = todoService.update(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(todo.getId()).toUri();
        return ResponseEntity.ok().header("Location", location.toString()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteById(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoResponse > getById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.get(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<TodoResponse>> getAll() {
        return ResponseEntity.ok(todoService.getAll(1, 10));
    }
}
