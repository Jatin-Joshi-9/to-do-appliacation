package com.example.todoapplication.service;

import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Task;
import com.example.todoapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(String title, String description, Priority priority) {
        if (taskRepository.isExistsByTitle(title)) {
            throw new IllegalArgumentException("Task with title '" + title + "' already exists.");
        }
        Task task = new Task(title, description, priority);
        return taskRepository.save(task);
    }
}
