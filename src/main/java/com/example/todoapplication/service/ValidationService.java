package com.example.todoapplication.service;

import com.example.todoapplication.model.Priority;

import com.example.todoapplication.model.Status;

public class ValidationService {
    public static boolean isValidTitle(String title) {
        return title != null && !title.isBlank() && !title.isEmpty() && title.trim().length() <= 100;
    }
    public static boolean isValidDescription(String description) {
        return description != null && !description.isBlank()  && description.trim().length() <= 500;
    }
    public static boolean isValidStatus(Status status) {
        if(status == null) {
            return false;
        }
        return status.equals(Status.PENDING) || status.equals(Status.IN_PROGRESS) || status.equals(Status.COMPLETED);
    }
    public static boolean isValidPriority(Priority priority) {
        if(priority == null) {
            return false;
        }
        return priority.equals(Priority.LOW) || priority.equals(Priority.MEDIUM) || priority.equals(Priority.HIGH);
    }

}
