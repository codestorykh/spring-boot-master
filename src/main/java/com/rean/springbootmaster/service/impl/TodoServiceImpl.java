package com.rean.springbootmaster.service.impl;

import com.rean.springbootmaster.dto.TodoBatchRequest;
import com.rean.springbootmaster.dto.TodoBatchResponse;
import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.model.Todo;
import com.rean.springbootmaster.repository.TodoRepository;
import com.rean.springbootmaster.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;


    @Override
    public TodoResponse create(TodoRequest todoRequest) {

        Todo todo = new Todo();
        todo.setName(todoRequest.name());
        todo.setDescription(todoRequest.description());
        todo.setStatus("ACT");
        todo = todoRepository.save(todo);
        return new TodoResponse(todo.getId(), todo.getName(), todo.getDescription());
    }

    @Override
    public void batchCreate(List<TodoBatchRequest> todoRequests) {
        if(!todoRequests.isEmpty()) {
            List<Todo> todos = new ArrayList<>();
            for(TodoBatchRequest req : todoRequests) {
                todos.add(modelMapper.map(req, Todo.class));
            }
            todoRepository.saveAll(todos);
        }
    }

    //    public void saveOrUpdate(TodoRequest todoRequest) {
//        if(null != todoRequest.id()) {
//            Optional<Todo> todoOptional = todoRepository.findFirstById(todoRequest.id());
//            if(todoOptional.isPresent()) {
//                todoOptional.get().setId(todoRequest.id());
//                todoOptional.get().setName(todoRequest.name());
//                todoOptional.get().setDescription(todoRequest.description());
//                todoRepository.saveAndFlush(todoOptional.get());
//            }
//        }else {
//            Todo todo = Todo.builder()
//                    .name(todoRequest.name())
//                    .description(todoRequest.description())
//                    .build();
//            todoRepository.saveAndFlush(todo);
//        }
//    }
    @Override
    public TodoResponse update(TodoRequest todoRequest, long id) {

        Optional<Todo> todoOptional = todoRepository.findFirstByIdAndStatus(id, "ACT");
        if (todoOptional.isPresent()) {
            todoOptional.get().setId(id);
            todoOptional.get().setName(todoRequest.name());
            todoOptional.get().setDescription(todoRequest.description());
            Todo todo = todoRepository.saveAndFlush(todoOptional.get());
            return new TodoResponse(todo.getId(), todo.getName(), todo.getDescription());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
//        todoRepository.deleteById(id);

//        Optional<Todo> todo = todoRepository.findFirstById(id);
//        if(todo.isPresent()) {
//            todoRepository.delete(todo.get());
//        }
//        todoRepository.findFirstById(id).ifPresent(todoRepository::delete);
        Optional<Todo> todo = todoRepository.findFirstByIdAndStatus(id, "ACT");
        if (todo.isPresent()) {
            todo.get().setStatus("DEL");
            todoRepository.save(todo.get());
        }
    }

    @Override
    public TodoResponse get(Long id) {
//        Optional<Todo> todo = todoRepository.findFirstById(id);
//        if (todo.isPresent()) {
            //more conditions
//            return new TodoResponse(todo.get().getId(), todo.get().getName(), todo.get().getDescription());
//        }
        return todoRepository.findFirstById(id)
                .map(todo -> new TodoResponse(todo.getId(), todo.getName(), todo.getDescription()))
                .orElse(null);
    }

    @Override
    public List<TodoBatchResponse> getAll(Integer page, Integer size) {

        // page = 0; size = 10
        if(page == null || size == null) {
            page = 0;
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Todo> todos = todoRepository.findAll(pageable);
        if(todos.getContent().isEmpty()) {
            log.info("No data found");
            return new ArrayList<>();
        }

        List<TodoBatchResponse> todoResponses = new ArrayList<>();
        for(Todo todo : todos.getContent()) {
//           todoResponses.add(
//                   TodoBatchResponse.builder()
//                           .id(todo.getId())
//                           .name(todo.getName())
//                           .description(todo.getDescription())
//                           .status(todo.getStatus())
//                           .totalPage(todos.getTotalPages())
//                           .totalRecord(todos.getTotalElements())
//                           .currentPage(page + 1)
//                           .pageSize(size)
//                           .build());

            todoResponses.add(modelMapper.map(todo, TodoBatchResponse.class));
        }
        return todoResponses;
    }

}
