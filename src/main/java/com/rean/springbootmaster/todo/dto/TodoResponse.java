package com.rean.springbootmaster.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TodoResponse {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
