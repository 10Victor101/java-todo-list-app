package com.example.todolist.dto.grupoDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record GrouperRequestDTO(
        @Schema(description = "Nome do agrupador", requiredMode = Schema.RequiredMode.REQUIRED)
        String name
) {
}