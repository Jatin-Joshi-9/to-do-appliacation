package com.example.todoapplication.service;

import com.example.todoapplication.dto.TaskRequest;
import com.example.todoapplication.exception.DuplicateTitleException;
import com.example.todoapplication.exception.TaskIdNotExistException;
import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Status;
import com.example.todoapplication.model.Task;
import com.example.todoapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean isTitleExist(String title) {
        return taskRepository.isExistsByTitle(title);
    }

    public Task create(TaskRequest request) {
        String title = request.getTitle().trim();
        if (isTitleExist(title)) {
            throw new DuplicateTitleException("Task with the " + title + " title already exists");
        }

        Task task = new Task(title,
                request.getDescription().trim(),
                request.getPriority(), request.getStatus());

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(Status status, Priority priority) {
        return taskRepository.findAll().stream()
                .filter(task -> status == null || task.getStatus() == status)
                .filter(task -> priority == null || task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public Task getTaskById(String id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new TaskIdNotExistException("Task not found with id: " + id);
        }
        return task;
    }

    public void deleteTask(String id) {
        if (!taskRepository.isExistsById(id)) {
            throw new TaskIdNotExistException("Task with id " + id + " does not exist");
        }
        taskRepository.deleteById(id);
    }

    public Task update(String id, TaskRequest request) {
        Task existingTask = getTaskById(id);
        boolean updated = false;

        if (ValidationService.isValidDescription(request.getDescription())) {
            String newDesc = request.getDescription().trim();
            if (!newDesc.equalsIgnoreCase(existingTask.getDescription())) {
                existingTask.setDescription(newDesc);
                updated = true;
            }
        }

        if (request.getStatus() != null) {
            Status statusEnum = request.getStatus();
            if (statusEnum != null) {
                existingTask.setStatus(statusEnum);
                updated = true;
            }
        }

        if (request.getPriority() != null) {
            Priority priorityEnum = request.getPriority();
            if (priorityEnum != null) {
                existingTask.setPriority(priorityEnum);
                updated = true;
            }
        }

        if (ValidationService.isValidTitle(request.getTitle())) {
            String newTitle = request.getTitle().trim();

            if (!newTitle.equalsIgnoreCase(existingTask.getTitle()) && isTitleExist(newTitle)) {

                throw new DuplicateTitleException(
                        "Task with the " + newTitle + " title already exists");
            }

            if (!newTitle.equalsIgnoreCase(existingTask.getTitle())) {
                existingTask.setTitle(newTitle);
                updated = true;
            }
        }

        if (updated) {
            existingTask.setUpdatedAt();
            return taskRepository.save(existingTask);
        }
        return existingTask;
    }

}
