package com.example.todoapplication.dto;

import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskRequest {
    @Size(max = 100, message = "Title max 100 chars")
    @NotBlank(message = "Title is required")
    private String title;

    @Size(max = 500, message = "Description max 500 chars")
    @NotBlank(message = "Description is required")
    private String description;

    private Status status;

    @NotNull(message = "Priority is required")
    private Priority priority;

    public String getTitle() {
        return title;
    }


}