package com.example.todoapplication.exception;

public class TaskIdNotExistException extends RuntimeException {
    public TaskIdNotExistException(String message) {
        super(message);
    }
    
}
