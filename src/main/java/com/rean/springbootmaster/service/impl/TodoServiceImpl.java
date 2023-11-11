package com.rean.springbootmaster.service.impl;

import com.rean.springbootmaster.dto.TodoRequest;
import com.rean.springbootmaster.dto.TodoResponse;
import com.rean.springbootmaster.model.Todo;
import com.rean.springbootmaster.repository.TodoRepository;
import com.rean.springbootmaster.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoResponse create(TodoRequest todoRequest) {

        Todo todo = new Todo();
        todo.setName(todoRequest.name());
        todo.setDescription(todoRequest.description());
        todo.setStatus("ACT");
        todo = todoRepository.save(todo);
        return new TodoResponse(todo.getId(), todo.getName(), todo.getDescription());
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
    public TodoResponse update(TodoRequest todoRequest) {

        Optional<Todo> todoOptional = todoRepository.findFirstByIdAndStatus(todoRequest.id(), "ACT");
        if(todoOptional.isPresent()) {
            todoOptional.get().setId(todoRequest.id());
            todoOptional.get().setName(todoRequest.name());
            todoOptional.get().setDescription(todoRequest.description());
           Todo todo = todoRepository.saveAndFlush(todoOptional.get());
            return new TodoResponse(todo.getId(), todo.getName(), todo.getDescription());
        }
       return null;
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);

//        Optional<Todo> todo = todoRepository.findFirstById(id);
//        if(todo.isPresent()) {
//            todoRepository.delete(todo.get());
//        }
//        todoRepository.findFirstById(id).ifPresent(todoRepository::delete);
       Optional<Todo> todo = todoRepository.findFirstByIdAndStatus(id, "ACT");
       if(todo.isPresent()){
           todo.get().setStatus("DEL");
           todoRepository.save(todo.get());
       }
    }

    @Override
    public TodoResponse get(Long id) {
        return null;
    }

    @Override
    public List<TodoResponse> getAll(int page, int size) {
        return null;
    }
}
