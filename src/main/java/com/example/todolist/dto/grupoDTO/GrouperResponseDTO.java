package com.example.todolist.dto.grupoDTO;

import com.example.todolist.model.Grouper;
import io.swagger.v3.oas.annotations.media.Schema;

public record GrouperResponseDTO(
        @Schema(description = "Nome do agrupador")
        Grouper grouper
) {
}
