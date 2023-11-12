package com.rean.springbootmaster.controller;


import com.rean.springbootmaster.dto.TodoBatch;
import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(
    value = "/api/todos",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)

@CrossOrigin(origins = "http://localhost:8181")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody TodoRequest todoRequest) {
        log.info("Intercept create todo request {}", todoRequest);
        return new ResponseEntity<>(todoService.create(todoRequest), HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<Object> batchCreate(@RequestBody TodoBatch todoBatch) {
        return new ResponseEntity<>(new Object(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id,
                                               @RequestBody TodoRequest todoRequest) {
        log.info("Intercept update todo request {}", todoRequest);
        return new ResponseEntity<>(todoService.update(todoRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Intercept delete todo request {}", id);
        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> checkById(@PathVariable Long id) {
        log.info("Intercept check by id todo request {}", id);
        return new ResponseEntity<>(todoService.get(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size) {
        log.info("Intercept get all todo request");
        return new ResponseEntity<>(todoService.getAll(page, size), HttpStatus.OK);
    }

}
