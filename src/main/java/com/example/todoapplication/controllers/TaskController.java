package com.example.todoapplication.controllers;

import com.example.todoapplication.model.Task;
import com.example.todoapplication.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
}