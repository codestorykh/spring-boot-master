package com.rean.springbootmaster.todo.provider;

import com.rean.springbootmaster.todo.constant.Constant;
import com.rean.springbootmaster.todo.dto.TodoRequest;
import com.rean.springbootmaster.todo.dto.TodoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class TodoClientServiceImpl implements TodoClientService{

    private final RestClient restClient;
    private final WebClient webClient;

    public TodoClientServiceImpl(@Value("${api.base.url:http://localhost:8080/}") String baseUrl) {
        this.restClient = RestClient.create(baseUrl);
        this.webClient = WebClient.create(baseUrl);
    }
    @Override
    public List<TodoResponse> getAllTodos() {
        return restClient.get()
                .uri(Constant.API_BASE_URL)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public TodoResponse getTodoById(long id) {
        return restClient.get()
                .uri(Constant.API_BASE_URL + "{id}", id)
                .retrieve()
                .body(TodoResponse.class);
    }

    @Override
    public void deleteTodoById(long id) {
        restClient.delete()
                .uri(Constant.API_BASE_URL + "/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void create(TodoRequest todoRequest) {
        restClient.post()
                .uri(Constant.API_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(todoRequest)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void update(long id, TodoRequest todoRequest) {
        restClient.put()
                .uri(Constant.API_BASE_URL + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(todoRequest)
                .retrieve()
                .toBodilessEntity();
    }


    // using web client
    public Flux<List<TodoResponse>> getAll() {
        return webClient.get().uri(Constant.API_BASE_URL)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<>() {
                });
    }

    // using web client
    public Mono<TodoResponse> getById(Integer id) {
        return webClient.get().uri(Constant.API_BASE_URL + "/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (response) -> {
                    throw new ResponseStatusException(
                            response.statusCode()
                    );
                })
                .bodyToMono(TodoResponse.class);
    }
}
