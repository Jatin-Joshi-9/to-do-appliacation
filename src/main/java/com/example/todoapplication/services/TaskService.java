package com.example.todoapplication.services;

import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Status;
import com.example.todoapplication.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new HashMap<>();
    private final Map<Long, Task> mockTasks = new HashMap<>();

    private Long nextId = 1L;
    private final boolean useMockData = true; // remove once database is integrated

    public List<Task> getFilteredTasks(Status status, Priority priority){

        // Mock data to be removed later
        Task t1 = new Task("Read Clean code book", "Set a reading time", Priority.LOW);
        mockTasks.put(nextId, t1);

        nextId += 1;

        Task t2 = new Task("Review pending Pull request", "", Priority.HIGH);
        mockTasks.put(nextId, t2);

        if(useMockData){
            return mockTasks.values().stream()
                    .filter(task -> status == null || task.getStatus() == status)
                    .filter(task -> priority == null || task.getPriority() == priority)
                    .toList();
        } else {
            return tasks.values().stream()
                    .filter(task -> status == null || task.getStatus() == status)
                    .filter(task -> priority == null || task.getPriority() == priority)
                    .toList();
        }
    }

}
