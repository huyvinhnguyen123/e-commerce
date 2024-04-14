package com.e.commerce.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "userId", updatable = false)
    private String userId;
    @Column(name = "userName", unique = true, nullable = false)
    private String userName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    // For prepare deleting user
    @Column(name = "lockFlag", nullable = false)
    private int lockFlag;
    @Column(name = "deleteFlag", nullable = false)
    private int deleteFlag;
    @Column(name = "oldLoginId")
    private String oldLoginId;

    @Column(name = "createAt")
    private LocalDate createAt;
    @Column(name = "updateAt")
    private LocalDate updateAt;

    public User() {
        this.userId = UUID.randomUUID().toString();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public User(String userName, String email) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
