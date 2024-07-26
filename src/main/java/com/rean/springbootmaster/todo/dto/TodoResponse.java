package com.rean.springbootmaster.todo.dto;


public record TodoResponse(Long id, String title, String description, boolean completed) {
}
