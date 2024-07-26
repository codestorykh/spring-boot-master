package com.rean.springbootmaster.todo.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}