package com.rean.springbootmaster.user.controller;

import com.rean.springbootmaster.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/users",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void addTodo() {
        userService.addUser();
    }

    @PutMapping
    public void updateTodo() {
        userService.updateUser();
    }

    @DeleteMapping
    public void deleteTodo() {
        userService.deleteUser();
    }

    @GetMapping("/{id}")
    public void getTodo(@PathVariable String id) {
        userService.getUsers();
    }

    @GetMapping
    public void getTodos() {
        userService.getUsers();
    }
}
