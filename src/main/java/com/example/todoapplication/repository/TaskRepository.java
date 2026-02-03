package com.example.todoapplication.repository;

import com.example.todoapplication.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Repository
public class TaskRepository {

    private Map<String,Task> taskMap = new HashMap<>();

    public Task save(Task task) {

        taskMap.put(task.getId(), task);
        return task;
    }
    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }
}