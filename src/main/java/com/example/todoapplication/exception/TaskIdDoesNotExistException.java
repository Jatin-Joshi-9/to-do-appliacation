package com.example.todoapplication.exception;

public class TaskIdDoesNotExistException extends RuntimeException {
    public TaskIdDoesNotExistException(String message) {
        super(message);
    }
    
}
