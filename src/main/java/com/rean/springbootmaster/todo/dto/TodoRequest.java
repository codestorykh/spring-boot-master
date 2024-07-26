package com.rean.springbootmaster.todo.dto;

public record TodoRequest(Long id, String title, String description, boolean completed) {
}
