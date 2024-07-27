package com.rean.springbootmaster.todo.provider;

import com.rean.springbootmaster.todo.dto.TodoRequest;
import com.rean.springbootmaster.todo.dto.TodoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

import static com.rean.springbootmaster.todo.constant.Constant.API_BASE_URL;

public interface TodoClientService {

    @GetExchange(API_BASE_URL)
    List<TodoResponse> getAllTodos();

    @GetExchange(API_BASE_URL + "/{id}")
    TodoResponse getTodoById(@PathVariable("id") long id);

    @DeleteExchange(API_BASE_URL + "/{id}")
    void deleteTodoById(@PathVariable("id") long id);

    @PostExchange(API_BASE_URL)
    void create(@RequestBody TodoRequest todoRequest);

    @PutExchange(API_BASE_URL + "/{id}")
    void update(@PathVariable("id") long id,
                    @RequestBody TodoRequest todoRequest);
}
