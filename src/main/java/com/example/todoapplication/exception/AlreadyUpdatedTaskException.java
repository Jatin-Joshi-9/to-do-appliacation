package com.example.todoapplication.exception;

public class AlreadyUpdatedTaskException extends RuntimeException {
    public AlreadyUpdatedTaskException(String message) {
        super(message);
    }
}
