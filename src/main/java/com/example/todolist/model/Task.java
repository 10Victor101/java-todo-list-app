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
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SoftDelete
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da tarefa", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;
    @Schema(description = "Nome da tarefa", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Descrição da tarefa", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @ManyToOne
    @Schema(description = "ID do agrupador da tarefa")
    @JoinColumn(name = "grouper_id")
    private Grouper grouper;
    @Schema(description = "Data de vencimento da tarefa", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "expiration_at")
    private Instant expirationAt;
    @Hidden
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @Hidden
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
