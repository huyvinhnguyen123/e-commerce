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
    @Column(name = "roleId", updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer roleId;
    @Column(name = "roleName", nullable = false)
    private String roleName;
    @Column(name = "description")
    private String description;
    @Column(name = "isAdministration", nullable = false)
    private Boolean isAdministration;

    @Column(name = "createAt")
    private LocalDate createAt;
    @Column(name = "updateAt")
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
