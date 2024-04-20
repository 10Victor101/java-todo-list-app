package com.example.todolist.repositories;

import com.example.todolist.model.Grouper;
import com.example.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrouperRepository extends JpaRepository<Grouper, String> {
    List<Grouper> findAllByUser(User user);
}