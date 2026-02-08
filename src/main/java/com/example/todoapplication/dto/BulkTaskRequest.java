package com.example.todoapplication.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class BulkTaskRequest {
    
    @NotEmpty(message = "Tasks list cannot be empty")
    @Valid
    private List<TaskRequest> tasks;

    public List<TaskRequest> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskRequest> tasks) {
        this.tasks = tasks;
    }
}