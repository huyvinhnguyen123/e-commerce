package com.e.commerce.application.domain.services;

import com.e.commerce.application.domain.entities.Role;
import com.e.commerce.application.domain.repositories.RoleRepository;
import com.e.commerce.application.utils.enums.RoleData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleById(Integer roleId) {
        Role existRole = roleRepository.findById(roleId).orElseThrow(
                () -> {
                    log.error("Not found this id: {}", roleId);
                    return new NullPointerException("Not found this id: " + roleId);
                }
        );

        log.info("Found role");
        return existRole;
    }

    public Role findRoleByName(String roleName) {
        Role existRole = roleRepository.findByRoleName(roleName).orElseThrow(
                () -> {
                    log.error("Not found this role: {}", roleName);
                    return new NullPointerException("Not found this role: " + roleName);
                }
        );

        log.info("Found role");
        return existRole;
    }

    /**
     * Create role
     * @param roleData - input custom role
     */
    public Role addRole(RoleData roleData) {
        Role role = new Role();
        role.setRoleName(roleData.getRole());

        if (role.getRoleName().equals("ROLE_ADMIN") || role.getRoleName().equals("ROLE_SYSTEM")) {
            role.setIsAdministration(true);
        }else{
            role.setIsAdministration(false);
        }

        roleRepository.save(role);
        log.info("Create role success");

        return role;
    }
}
