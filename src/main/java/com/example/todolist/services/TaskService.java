package com.example.todolist.services;

import com.example.todolist.dto.taskDTO.TaskResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    TaskResponseDTO createTask(String name, String description, String expirationAt, String grouperId);
}
