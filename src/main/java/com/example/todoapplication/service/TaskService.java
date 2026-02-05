package com.example.todoapplication.service;

import com.example.todoapplication.dto.TaskRequest;
import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Status;
import com.example.todoapplication.model.Task;
import com.example.todoapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(TaskRequest request) {
        String title = request.getTitle().trim();
        title = title.replaceAll("\\s+", " ");
        if (taskRepository.isExistsByTitle(title)) {
            throw new IllegalArgumentException("Task with the same title already exists");
        }

        Task task = new Task( title,
                request.getDescription().trim(),
                request.getPriority() != null ? request.getPriority() : Priority.MEDIUM
        );


        return taskRepository.save(task);
    }
    public List<Task> getAllTasks(Status status, Priority priority) {
        return taskRepository.findAll().stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> priority == null || task.getPriority() == priority)
                .collect(Collectors.toList());
    }
    public Task getTaskById(String id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
        return task;
    }
    
    public Task update(String id, TaskRequest request) {
        Task existingTask = getTaskById(id);
        boolean updated = false;

        if (request.getTitle() != null && !request.getTitle().isBlank() && !request.getTitle().isEmpty() && request.getTitle().trim().length() <= 100) {
            String newTitle = request.getTitle().trim().replaceAll("\\s+", " ");
            if (!existingTask.getTitle().equalsIgnoreCase(newTitle)) { 
                if (taskRepository.isExistsByTitle(newTitle)) {
                    throw new IllegalArgumentException("Task with the same title already exists");
                }
                existingTask.setTitle(newTitle);
                updated = true;
            }
        }

        if (request.getDescription() != null && request.getDescription().trim().length() <= 500 && !request.getDescription().isBlank() && !request.getDescription().isEmpty()) {
            String newDesc = request.getDescription().trim();
            if (!newDesc.equalsIgnoreCase(existingTask.getDescription())) { 
                existingTask.setDescription(newDesc);
                updated = true;
            }
        }

        if (request.getPriority() != null && !request.getPriority().equals(existingTask.getPriority()) ) {
            existingTask.setPriority(request.getPriority());
            updated = true;
        }

        if (request.getStatus() != null && !request.getStatus().equals(existingTask.getStatus())) {
            existingTask.setStatus(request.getStatus());
            updated = true;
        }

        if (updated) {
            existingTask.setUpdatedAt();
            return taskRepository.save(existingTask);
        }
        else{
            throw new IllegalArgumentException("Already up to date fields provided");
        }
         
    }

}
