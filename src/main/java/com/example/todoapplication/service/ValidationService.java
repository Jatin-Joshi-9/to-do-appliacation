package com.example.todoapplication.service;

public class ValidationService {
    public static boolean isValidTitle(String title) {
        return title != null && !title.isBlank() && !title.isEmpty() && title.trim().length() <= 100 && title.isEmpty();
    }
    public static boolean isValidDescription(String description) {
        return description != null && !description.isBlank() && !description.isEmpty() && description.trim().length() <= 500;
    }
}
