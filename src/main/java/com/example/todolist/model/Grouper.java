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
@Table(name = "grouper")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SoftDelete
public class Grouper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do grupo", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;
    @Schema(description = "Nome do grupo", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Usuário do grupo", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Hidden
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @Hidden
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
