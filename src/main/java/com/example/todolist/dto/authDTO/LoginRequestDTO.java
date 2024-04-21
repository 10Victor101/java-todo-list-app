package com.example.todolist.dto.authDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequestDTO(
        @Schema(description = "E-mail do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @Schema(description = "Senha do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {
}