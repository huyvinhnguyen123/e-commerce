package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.Permission;
import com.e.commerce.application.domain.repositories.object.PermissionSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByPermissionName(String permissionName);

    @Query(value = """
                SELECT p.permission_id, p.permission_name, p.description, p.page_key, p.is_active, p.role_name
                FROM permissions p
                LEFT JOIN Role r ON p.role_id = r.role_id
                WHERE p.role_id = :roleId
            """, nativeQuery = true)
    List<PermissionSelect> findByRoleId(@Param("roleId") Long roleId);
}
