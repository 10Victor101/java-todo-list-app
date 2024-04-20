package com.example.todolist.services;

import com.example.todolist.dto.grupoDTO.GrouperResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface GrouperService {
    GrouperResponseDTO createGroup(String name, long userId);
}
