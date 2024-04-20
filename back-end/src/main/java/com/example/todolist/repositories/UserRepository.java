package com.example.todolist.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todolist.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String username);
    Optional<User> findUserById(Long id);
    boolean existsByEmail(String email);
}
