package com.example.todolist.services;

import com.example.todolist.dto.grupoDTO.GrouperResponseDTO;
import com.example.todolist.model.Grouper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrouperService {
    GrouperResponseDTO createGrouper(String name, String userId);

    List<Grouper> getGroupers(String userId);

    void deleteGrouper(String id);
}
