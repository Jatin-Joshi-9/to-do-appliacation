package com.example.todoapplication.service;

import com.example.todoapplication.dto.TaskRequest;
import com.example.todoapplication.exception.DuplicateTitleException;
import com.example.todoapplication.model.Task;
import com.example.todoapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TaskService {
    private final TaskRepository dbTaskRepository;  // For task creation

    public TaskService(TaskRepository dbTaskRepository) {
        this.dbTaskRepository = dbTaskRepository;
    }



    public boolean isTitleExist(String title) {
        
        return dbTaskRepository.existsByTitleIgnoreCase(title.trim());
    }

    public Task create(TaskRequest request) {
        String title = request.getTitle().trim();
        if (isTitleExist(title)) {
            throw new DuplicateTitleException("Task with the " + title + " title already exists");
        }

        Task task = new Task(title,
                request.getDescription().trim(),
                request.getPriority(),
                request.getStatus());

        return dbTaskRepository.save(task);  
    }

    public List<Task> createBulk(List<TaskRequest> requests) {
     
        Set<String> titlesInRequest = new HashSet<>();
        for (TaskRequest request : requests) {
            String title = request.getTitle().trim().toLowerCase();

            if (titlesInRequest.contains(title)) {
                throw new DuplicateTitleException(
                        "Duplicate title " + request.getTitle() + " found in bulk request"
                );
            }
            titlesInRequest.add(title);
        }


        for (TaskRequest request : requests) {
            String title = request.getTitle().trim();

            if (isTitleExist(title)) {
                throw new DuplicateTitleException(
                        "Task title " + title + " already exists"
                );
            }
        }

     
        List<Task> createdTasks = new ArrayList<>();
        for (TaskRequest request : requests) {
            Task task = create(request);
            createdTasks.add(task);
        }

        return createdTasks;
    }

   

    
}