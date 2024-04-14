package com.e.commerce.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Permissions")
public class Permission {
    @Id
    @Column(name = "permissionId", updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer permissionId;
    @Column(name = "permissionName", unique = true, nullable = false)
    private String permissionName;
    @Column(name = "description")
    private String description;
    @Column(name = "pageKey", unique = true, nullable = false)
    private String pageKey;
    @Column(name = "isActive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    @Column(name = "createAt")
    private LocalDate createAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;

    public Permission() {
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Permission(String permissionName, String pageKey, Boolean isActive) {
        this.permissionName = permissionName;
        this.pageKey = pageKey;
        this.isActive = isActive;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Permission(String permissionName, String description, String pageKey, Boolean isActive)  {
        this.permissionName = permissionName;
        this.description = description;
        this.pageKey = pageKey;
        this.isActive = isActive;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}