package com.example.todolist.dto.taskDTO;

import com.example.todolist.model.Task;
import io.swagger.v3.oas.annotations.media.Schema;

public record TaskResponseDTO(
        @Schema(description = "Tarefa")
        Task task
) {
}
