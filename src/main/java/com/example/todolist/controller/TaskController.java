package com.example.todolist.controller;

import com.example.todolist.dto.ResponseDTO;
import com.example.todolist.dto.taskDTO.TaskRequestDTO;
import com.example.todolist.dto.taskDTO.TaskResponseDTO;
import com.example.todolist.exceptions.groupExceptions.GrouperNotFoundException;
import com.example.todolist.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@Tag(name = "Task Controller", description = "Endpoints para operações de tarefas")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    @Operation(summary = "Criar uma nova tarefa", description = "Endpoint para criar uma nova tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
            content = @Content(schema = @Schema(implementation = TaskResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida ou agrupador não encontrado",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    public ResponseEntity<?> createTask(@RequestBody TaskRequestDTO body) {
        try {
            TaskResponseDTO taskResponseDTO = taskService.createTask(body.name(), body.description(), body.expirationAt(), body.grouperId());
            return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDTO);
        } catch (IllegalArgumentException | GrouperNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        }
    }
}
