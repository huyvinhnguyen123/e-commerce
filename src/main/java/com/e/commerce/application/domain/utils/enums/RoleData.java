package com.e.commerce.application.domain.utils.enums;

import lombok.Getter;

@Getter
public enum RoleData {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    SYSTEM("ROLE_SYSTEM"),
    AUTHENTICATED("AUTHENTICATED"),
    PERMIT_ALL("PERMIT_ALL");

    private final String role;

    RoleData(String role){
        this.role = role;
    }
}
