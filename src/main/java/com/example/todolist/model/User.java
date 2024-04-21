package com.example.todolist.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SoftDelete
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;
    @Schema(description = "Nome do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Column(unique = true)
    @Schema(description = "E-mail do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(description = "Senha do usuário", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Hidden
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @Hidden
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
