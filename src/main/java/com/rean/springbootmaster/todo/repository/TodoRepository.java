package com.rean.springbootmaster.todo.repository;

import com.rean.springbootmaster.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for communication with database
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
}