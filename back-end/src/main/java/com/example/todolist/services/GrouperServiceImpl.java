package com.example.todolist.services;

import com.example.todolist.dto.grupoDTO.GrouperResponseDTO;
import com.example.todolist.exceptions.authExceptions.UserNotFoundException;
import com.example.todolist.model.Grouper;
import com.example.todolist.model.User;
import com.example.todolist.repositories.GrouperRepository;
import com.example.todolist.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouperServiceImpl implements GrouperService {
    private final GrouperRepository grouperRepository;
    private final UserRepository userRepository;

    public GrouperServiceImpl(GrouperRepository grouperRepository, com.example.todolist.repositories.UserRepository userRepository) {
        this.grouperRepository = grouperRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GrouperResponseDTO createGrouper(String name, long userId) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome não informado.");
        }

        User user = userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException("O usuário não foi encontrado para vincular ao grupo."));

        Grouper grouper = new Grouper();
        grouper.setName(name);
        grouper.setUser(user);
        grouperRepository.save(grouper);

        return new GrouperResponseDTO(grouper);
    }

    @Override
    public List<Grouper> getGroupers(long userId) {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException("O usuário não foi encontrado para encontrar os grupos."));
        return grouperRepository.findAllByUser(user);
    }
}
