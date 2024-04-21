package com.example.todolist.dto.taskDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record TaskRequestDTO(
        @Schema(description = "Nome da tarefa", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @Schema(description = "Descrição da tarefa")
        String description,

        @Schema(description = "Data de vencimento", requiredMode = Schema.RequiredMode.REQUIRED)
        String expirationAt,

        @Schema(description = "Identificador do agrupamento", requiredMode = Schema.RequiredMode.REQUIRED)
        String grouperId) {
}