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

    private List<Task> taskList = new ArrayList<>();
    public Task save(Task task) {
        taskList.add(task);
        return task;
    }
}