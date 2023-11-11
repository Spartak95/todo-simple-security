package com.apress.todo.domain;

import java.time.LocalDateTime;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class ToDo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String id;
    @Nonnull
    @NotBlank
    private String description;
    @Column(updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;

    public ToDo() {

    }

    public ToDo(String description) {
        this.description = description;
    }

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
