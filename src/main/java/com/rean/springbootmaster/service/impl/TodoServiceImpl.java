package com.rean.springbootmaster.service.impl;

import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.exception.ResourceNotFoundException;
import com.rean.springbootmaster.model.Todo;
import com.rean.springbootmaster.repository.TodoRepository;
import com.rean.springbootmaster.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoResponse create(TodoRequest todoRequest) {
       Todo todo = todoRepository.save(Todo.builder()
                        .name(todoRequest.name())
                        .description(todoRequest.description())
                .build());
       return new TodoResponse(todo.getId(), todo.getName(), todo.getDescription());
    }

    @Override
    public TodoResponse update(TodoRequest todoRequest) {
        Optional<Todo> todo = todoRepository.findFirstById(todoRequest.id());
        if(todo.isEmpty()) {
            log.error("Todo not found with id: {}", todoRequest.id());
            throw new ResourceNotFoundException(String.format("Update Todo with ID %s not found", todoRequest.id()));
        }
        todo.get().setName(todoRequest.name());
        todo.get().setDescription(todoRequest.description());
        todoRepository.save(todo.get());
        return new TodoResponse(todo.get().getId(), todo.get().getName(), todo.get().getDescription());
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public TodoResponse get(Long id) {
        Optional<Todo> todo = todoRepository.findFirstById(id);
        if(todo.isEmpty()) {
            log.error("Todo not found with id: {}", id);
            throw new ResourceNotFoundException(String.format("Todo ID %s not found", id));
        }
        return todo.map(value ->
                new TodoResponse(value.getId(), value.getName(), value.getDescription())).orElse(null);
    }

    @Override
    public List<TodoResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Todo> todos = todoRepository.findAll(pageable);
        return todos.stream()
                .map(todo ->
                        new TodoResponse(todo.getId(), todo.getName(), todo.getDescription()))
                .toList();
    }

}
