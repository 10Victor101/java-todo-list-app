package com.example.todolist.dto.taskDTO;

public record TaskRequestDTO(String name, String description, String expirationAt, String grouperId) {
}