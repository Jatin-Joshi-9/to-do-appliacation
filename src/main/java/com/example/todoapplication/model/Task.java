package com.example.todoapplication.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity  // i tells JPA: "This is a database table"
@Table(name = "tasks")  // Table name in database
public class Task {
    
    @Id  // Primary key
    @Column(name = "id", nullable = false, updatable = false)
    private String id;
    
    @Column(name = "title", nullable = false, length = 100, unique = true)
    private String title;
    
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    
    @Enumerated(EnumType.STRING)  // Store enum as string (PENDING)
    @Column(name = "status", nullable = true)
    private Status status;      //pending, in_progress, completed
    
    @Enumerated(EnumType.STRING)  // Store enum as string (HIGH)
    @Column(name = "priority", nullable = true)
    private Priority priority;  // low, medium, high
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
// default constructor as req by jpa
    public Task() {
    }

    
    public Task(String title, String description, Priority priority, Status status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        LocalDateTime currentTime = LocalDateTime.now();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}