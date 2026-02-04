package com.example.todoapplication.repository;

import com.example.todoapplication.model.Task;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class  TaskRepository {
    private final Map<String, Task> taskMapByID = new HashMap<>();
    public Task save(Task task) {
        taskMapByID.put(task.getId(), task);
        return task;
    }


}
