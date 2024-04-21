package com.example.todolist.dto.authDTO;

import com.example.todolist.model.User;

public record AuthResponseDTO(User user, String token) {
}
