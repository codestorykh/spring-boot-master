package com.rean.springbootmaster.todo.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class TodoClientConfig {

    @Bean("todo-rest-client")
    TodoClientService todoClientService(@Value("${api.base.url:http://localhost:8080}") String baseUrl) {
        RestClient restClient = RestClient.create(baseUrl);
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(TodoClientService.class);
    }

    @Primary
    @Bean("todo-web-client")
    TodoClientService todoWebClientService(@Value("${api.base.url:http://localhost:8080}") String baseUrl) {
        WebClient restClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultStatusHandler(HttpStatusCode::isError, clientResponse -> {
                    throw new ResponseStatusException(clientResponse.statusCode());
                }).build();

        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(restClient))
                .build()
                .createClient(TodoClientService.class);
    }
}
