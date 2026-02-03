package com.example.todoapplication.model;

import java.time.LocalDateTime;
import java.util.UUID;


public class Task {
    private final String id;
    private String title;
    private String description;
    private Status status;      //pending, in_progress, completed
    private Priority priority;  // low, medium, high
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Task(String title, String description, Priority priority) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
