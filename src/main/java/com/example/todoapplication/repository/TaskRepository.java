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

    public boolean isExistsByTitle(String title) {
        title = title.trim();
        title= title.replaceAll("\\s+", " ");
        String finalTitle = title;
        return taskMapByID.values().stream()
                .anyMatch(task -> task.getTitle().equalsIgnoreCase(finalTitle));
    }
    public List<Task> findAll() {
        return new ArrayList<>(taskMapByID.values());
    }
    public Task findById(String id) {
        return taskMapByID.get(id);
    }
}
