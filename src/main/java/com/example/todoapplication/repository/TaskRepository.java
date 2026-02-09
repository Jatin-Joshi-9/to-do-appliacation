package com.example.todoapplication.repository;

import com.example.todoapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import java.util.Optional;


@Primary
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    
    // Check if task exists by title (case-insensitive)
    boolean existsByTitleIgnoreCase(String title);
    
    // Find task by title (case-insensitive)
    Optional<Task> findByTitleIgnoreCase(String title);
}
