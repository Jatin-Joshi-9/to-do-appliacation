package com.example.todoapplication.services;

import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new HashMap<>();
    private Long nextId = 1L;

    public List<Task> getAllTasks(){

        // Dummy data
        Task t1 = new Task("test", "test", Priority.LOW);
        tasks.put(nextId, t1);

        return new ArrayList<>(tasks.values());
    }

}
