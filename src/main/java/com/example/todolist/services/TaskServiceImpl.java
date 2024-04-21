package com.example.todolist.services;

import com.example.todolist.dto.taskDTO.TaskResponseDTO;
import com.example.todolist.exceptions.groupExceptions.GrouperNotFoundException;
import com.example.todolist.model.Grouper;
import com.example.todolist.model.Task;
import com.example.todolist.repositories.GrouperRepository;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TaskServiceImpl implements TaskService {
    private final GrouperRepository grouperRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(GrouperRepository grouperRepository, TaskRepository taskRepository) {
        this.grouperRepository = grouperRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDTO createTask(String name, String description, String expirationAt, String grouperId) {
        if (name == null || expirationAt == null || name.isEmpty() || expirationAt.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos (nome, data de expiração) são obrigatórios.");
        }

        Grouper grouper = grouperRepository.findById(grouperId).orElseThrow(() -> new GrouperNotFoundException("Grupo para inserir a tarefa não foi encontrado!"));
        Task task = new Task();
        task.setGrouper(grouper);
        task.setName(name);
        task.setDescription(description);
        task.setExpirationAt(Instant.parse(expirationAt));

        taskRepository.save(task);

        return new TaskResponseDTO(task);
    }
}
