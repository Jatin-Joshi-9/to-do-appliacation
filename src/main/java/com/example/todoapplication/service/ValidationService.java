package com.example.todoapplication.service;



public class ValidationService {
    public static boolean isValidTitle(String title) {
        return title != null && !title.isBlank()  && title.trim().length() <= 100;
    }
    public static boolean isValidDescription(String description) {
        return description != null && !description.isBlank()  && description.trim().length() <= 500;
    }
   
}
