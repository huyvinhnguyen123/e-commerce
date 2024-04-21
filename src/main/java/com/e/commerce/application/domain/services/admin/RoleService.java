package com.e.commerce.application.domain.services.admin;

import com.e.commerce.application.domain.entities.Role;
import com.e.commerce.application.domain.exceptions.custom.DuplicateValueException;
import com.e.commerce.application.domain.repositories.RoleRepository;
import com.e.commerce.application.domain.utils.constant.Logger;
import com.e.commerce.application.domain.utils.enums.RoleData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleById(Long roleId) {
        Role existRole = roleRepository.findById(roleId).orElseThrow(
                () -> {
                    log.error("Not found this id: {}", roleId);
                    return new NullPointerException("Not found this id: " + roleId);
                }
        );

        log.info(Logger.findObjectSuccess("role"));
        return existRole;
    }

    public Role findRoleByName(String roleName) {
        Role existRole = roleRepository.findByRoleName(roleName).orElseThrow(
                () -> {
                    log.error("Not found this role: {}", roleName);
                    return new NullPointerException("Not found this role: " + roleName);
                }
        );

        log.info(Logger.findObjectSuccess("role"));
        return existRole;
    }

    /**
     * Create & Check list roles default
     */
    public void createAndCheckRoleDuplicate() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createListRolesDefault();
            log.info(Logger.createListObjectSuccess("roles"));
        } else {
            log.error(Logger.createObjectFail("role"));
            log.info("Role is existed");
            throw new DuplicateValueException("Role is existed");
        }
    }

    /**
     * Create list roles default
     */
    private void createListRolesDefault() {
        Set<Role> roles = new HashSet<>();
        for (RoleData roleData : RoleData.values()) {
            Role role = new Role();
            role.setRoleName(roleData.getRole());

            if (role.getRoleName().equals("ROLE_ADMIN") || role.getRoleName().equals("ROLE_SYSTEM")) {
                role.setIsAdministration(true);
            } else {
                role.setIsAdministration(false);
            }

            roles.add(role);
        }
        roleRepository.saveAll(roles);
    }

    /**
     * Create role
     * @param roleData - input custom role
     */
    public void addRole(RoleData roleData) {
        Role role = new Role();
        role.setRoleName(roleData.getRole());

        if (role.getRoleName().equals("ROLE_ADMIN") || role.getRoleName().equals("ROLE_SYSTEM")) {
            role.setIsAdministration(true);
        } else {
            role.setIsAdministration(false);
        }

        roleRepository.save(role);
        log.info(Logger.createObjectSuccess(role.getRoleName()));
    }

    /**
     * Create role
     * @param roleId - input role id
     */
    public void deleteRole(Long roleId) {
        Role existRole = findRoleById(roleId);
        roleRepository.delete(existRole);
        log.info(Logger.deleteObjectSuccess(existRole.getRoleName()));
    }
}
