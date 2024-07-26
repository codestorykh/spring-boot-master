package com.rean.springbootmaster.todo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TodoRequest {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
