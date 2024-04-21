package com.example.todolist.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseDTO(
        @Schema(description = "Mensagem de resposta")
        String message) {
}
