package com.e.commerce.application.domain.dtos.permission;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDataInput {
    private Long permissionId;

    @NotNull(message = "{permission.name.require}")
    @NotEmpty(message = "{permission.name.require}")
    private String serviceName;

    private String description;

    @NotNull(message = "{role.id.require}")
    @NotEmpty(message = "{role.id.require}")
    private Long roleId;
}
