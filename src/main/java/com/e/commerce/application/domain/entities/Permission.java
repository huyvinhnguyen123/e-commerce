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
    @Column(name = "permission_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long permissionId;
    @Column(name = "permission_name", unique = true, nullable = false)
    private String permissionName;
    @Column(name = "description")
    private String description;
    @Column(name = "page_key", unique = true, nullable = false)
    private String pageKey;
    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
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