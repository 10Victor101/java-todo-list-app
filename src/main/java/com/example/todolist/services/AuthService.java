package com.example.todolist.services;

import com.example.todolist.dto.ResponseDTO;
import com.example.todolist.dto.authDTO.AuthResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseDTO register(String name, String email, String password);
    AuthResponseDTO login(String email, String password);
}
