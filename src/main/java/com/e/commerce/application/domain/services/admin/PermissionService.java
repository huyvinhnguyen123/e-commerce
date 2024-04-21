package com.e.commerce.application.domain.services.admin;

import com.e.commerce.application.domain.dtos.permission.PermissionDataInput;
import com.e.commerce.application.domain.entities.Permission;
import com.e.commerce.application.domain.entities.Role;
import com.e.commerce.application.domain.repositories.PermissionRepository;
import com.e.commerce.application.domain.repositories.object.PermissionSelect;
import com.e.commerce.application.domain.utils.constant.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final RoleService roleService;
    private static final String PERMISSION_LOG = "permission";

    private Permission findByPermissionId(Long permissionId) {
        Permission existPermission = permissionRepository.findById(permissionId).orElseThrow(
                () -> new NullPointerException("Not found permission: " + permissionId)
        );

        log.info(Logger.findObjectSuccess(PERMISSION_LOG));
        return existPermission;
    }

    private Permission findByPermissionName(String permissionName) {
        Permission existPermission = permissionRepository.findByPermissionName(permissionName).orElseThrow(
                () -> new NullPointerException("Not found permission: " + permissionName)
        );

        log.info(Logger.findObjectSuccess(PERMISSION_LOG));
        return existPermission;
    }

    public boolean hasPermission(PermissionDataInput permissionDataInput) {
        Role existRole = roleService.findRoleById(permissionDataInput.getRoleId());
        List<Permission> permissions = new ArrayList<>();
        List<PermissionSelect> permissionSelects = permissionRepository.findByRoleId(existRole.getRoleId());
        for (PermissionSelect permissionSelect: permissionSelects) {
            Permission permission = new Permission();
            permission.setPermissionId(permissionSelect.getPermissionId());
            permission.setPermissionName(permissionSelect.getPermissionName());
            permission.setDescription(permissionSelect.getDescription());
            permission.setPageKey(permissionSelect.getPageKey());
            permission.setIsActive(permissionSelect.isActive());
            permission.setRole(existRole);

            permissions.add(permission);
        }
        return permissions.stream().anyMatch(permission -> permission.getPermissionName().equals(permissionDataInput.getServiceName()));
    }

    public void createPermission(PermissionDataInput permissionDataInput) {
        for (Permission existPermission: permissionRepository.findAll()) {
            if (permissionDataInput.getServiceName().equals(existPermission.getPermissionName())) {
                log.error(Logger.createObjectFail(PERMISSION_LOG));
                log.info("Permission is existed");
            } else {
                Permission permission = new Permission();
                permission.setPermissionName(permissionDataInput.getServiceName());
                permission.setDescription(permissionDataInput.getDescription());
                permission.setPageKey(permission.getPermissionName().trim());
                permission.setDescription(permissionDataInput.getDescription());

                Role existRole = roleService.findRoleById(permissionDataInput.getRoleId());
                permission.setRole(existRole);

                permissionRepository.save(permission);
            }
        }
        log.info(Logger.createObjectSuccess(PERMISSION_LOG));
    }

    public void updatePermission(PermissionDataInput permissionDataInput) {
        Permission existPermission = findByPermissionId(permissionDataInput.getPermissionId());
        existPermission.setPermissionName(permissionDataInput.getServiceName());
        existPermission.setPageKey(existPermission.getPermissionName().trim());
        existPermission.setDescription(permissionDataInput.getDescription());

        Role existRole = roleService.findRoleById(existPermission.getPermissionId());
        existPermission.setRole(existRole);

        permissionRepository.save(existPermission);
        log.info(Logger.updateObjectSuccess(PERMISSION_LOG));
    }

    public void deletePermission(PermissionDataInput permissionDataInput) {
        Permission existPermission = findByPermissionId(permissionDataInput.getPermissionId());
        permissionRepository.delete(existPermission);
        log.info(Logger.deleteObjectSuccess(PERMISSION_LOG));
    }
}
