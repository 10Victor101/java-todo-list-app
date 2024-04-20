package com.example.todolist.repositories;

import com.example.todolist.model.Grouper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrouperRepository extends JpaRepository<Grouper, String> {
}