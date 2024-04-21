package com.example.todolist.dto.authDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterRequestDTO(
        @Schema(description = "Nome do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @Schema(description = "E-mail do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @Schema(description = "Senha do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {
}
