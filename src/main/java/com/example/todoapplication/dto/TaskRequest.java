package com.example.todoapplication.dto;

import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {
    @Size(max = 100, message = "Title max 100 chars")
    @NotBlank(message = "Title is required")
    private String title;

    @Size(max = 500, message = "Description max 500 chars")
    @NotBlank(message = "Description is required")
    private String description;

   private String status;

   
    private String priority;

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Status getStatus() {
       return status == null ? null : Status.valueOf(status.toUpperCase());
    }
    public Priority getPriority() {
        return priority == null ? null : Priority.valueOf(priority.toUpperCase());
    }

}