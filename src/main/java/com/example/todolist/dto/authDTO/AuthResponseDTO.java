package com.example.todolist.dto.authDTO;

import com.example.todolist.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponseDTO(
        @Schema(description = "Usuário autenticado")
        User user,

        @Schema(description = "Token de autenticação")
        String token
) {
}