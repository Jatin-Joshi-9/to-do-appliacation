package com.example.todoapplication.service;

import com.example.todoapplication.model.Status;

public class ValidationService {
    public static boolean isValidTitle(String title) {
        return title != null && !title.isBlank() && !title.isEmpty() && title.trim().length() <= 100 && title.isEmpty();
    }
    public static boolean isValidDescription(String description) {
        return description != null && !description.isBlank() && !description.isEmpty() && description.trim().length() <= 500;
    }
    public static boolean isValidStatus(Status status) {
        return status == null || status.equals(Status.PENDING) || status.equals(Status.IN_PROGRESS) || status.equals(Status.COMPLETED);
    }
    
}
