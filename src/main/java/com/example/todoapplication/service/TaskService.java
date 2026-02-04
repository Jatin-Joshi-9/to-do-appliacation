package com.example.todoapplication.service;

import com.example.todoapplication.dto.TaskRequest;
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
}
