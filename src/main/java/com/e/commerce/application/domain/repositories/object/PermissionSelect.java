package com.e.commerce.application.domain.repositories.object;

public interface PermissionSelect {
    Long getPermissionId();
    String getPermissionName();
    String getDescription();
    String getPageKey();
    boolean isActive();
    Long getRoleId();
}
