package com.example.todoapplication.service;

import com.example.todoapplication.model.Priority;

import com.example.todoapplication.model.Status;

public class ValidationService {
    public static boolean isValidTitle(String title) {
        return title != null && !title.isBlank()  && title.trim().length() <= 100;
    }
    public static boolean isValidDescription(String description) {
        return description != null && !description.isBlank()  && description.trim().length() <= 500;
    }
    public static boolean isValidStatus(String status) {
        if (status == null) return false;
        try {
            Status.valueOf(status.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public static boolean isValidPriority(String priority) {
        if(priority == null) {
            return false;
        }
        try {
            Priority.valueOf(priority.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
