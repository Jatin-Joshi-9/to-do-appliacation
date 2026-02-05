package com.example.todoapplication.service;

public class ValidationService {
    public static boolean isValidTitle(String title) {
        return title != null && !title.isBlank() && !title.isEmpty() && title.trim().length() <= 100 && title.isEmpty();
    }
}
