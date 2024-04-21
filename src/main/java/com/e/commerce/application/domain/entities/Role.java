package com.e.commerce.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @Column(name = "role_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long roleId;
    @Column(name = "role_name", nullable = false)
    private String roleName;
    @Column(name = "description")
    private String description;
    @Column(name = "is_administration", nullable = false)
    private Boolean isAdministration;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Role() {
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Role(String roleName, Boolean isAdministration) {
        this.roleName = roleName;
        this.isAdministration = isAdministration;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Role(String roleName, String description, Boolean isAdministration) {
        this.roleName = roleName;
        this.description = description;
        this.isAdministration = isAdministration;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
