package com.example.todoapplication.exception;

public class TaskTitleExistException extends RuntimeException {
    public TaskTitleExistException(String message) {
        super(message);
    }
}
