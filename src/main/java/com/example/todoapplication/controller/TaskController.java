package com.example.todoapplication.controller;

import com.example.todoapplication.dto.TaskRequest;
import com.example.todoapplication.exception.DuplicateTitleException;
import com.example.todoapplication.exception.TaskIdNotExistException;
import com.example.todoapplication.model.Priority;
import com.example.todoapplication.model.Status;
import com.example.todoapplication.model.Task;
import com.example.todoapplication.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody TaskRequest request) {
        return taskService.create(request);
    }

    @GetMapping
    public List<Task> getAllTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority) {
        return taskService.getAllTasks(status, priority);
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable String id, @RequestBody TaskRequest request) {
        return taskService.update(id, request);
    }
        @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    @ExceptionHandler(value = DuplicateTitleException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDuplicateTitleException(DuplicateTitleException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("message", exception.getMessage());
        response.put("details", Map.of());
        return response;
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, Object> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
        response.put("message", "Method not allowed");
        response.put("details", Map.of());
        return response;
    }
    @ExceptionHandler(value = TaskIdNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleGenericException(TaskIdNotExistException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", exception.getMessage());
        response.put("details", Map.of());
        return response;
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Invalid request body");
        response.put("details", Map.of());
        return response;
    }



    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Invalid request body");
        List<String> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        response.put("details", Map.of("fieldErrors", fieldErrors));
        return response;
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNoResourceFoundException(NoResourceFoundException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", "Resource not found");
        response.put("details", Map.of());
        return response;
    }
}