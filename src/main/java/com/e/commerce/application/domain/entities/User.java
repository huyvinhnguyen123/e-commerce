package com.e.commerce.application.domain.entities;

import com.e.commerce.application.domain.utils.enums.DeleteFlag;
import com.e.commerce.application.domain.utils.enums.LockFlag;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @Column(name = "user_id", updatable = false)
    private String userId;
    @Column(name = "username", unique = true, nullable = false)
    private String userName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthDate;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "date_create")
    private LocalDate dateCreate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    // For prepare deleting user
    @Column(name = "lock_flag", nullable = false)
    private int lockFlag;
    @Column(name = "delete_flag", nullable = false)
    private int deleteFlag;
    @Column(name = "old_login_id")
    private String oldLoginId;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public User() {
        this.userId = UUID.randomUUID().toString();
        this.dateCreate = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public User(String userName, String email) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.dateCreate = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (StringUtils.hasText(role.getRoleName())) {
            return Lists.newArrayList(new SimpleGrantedAuthority(role.getRoleName()));
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return lockFlag == LockFlag.NON_LOCK.getCode();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return deleteFlag == DeleteFlag.NON_DELETE.getCode();
    }
}
