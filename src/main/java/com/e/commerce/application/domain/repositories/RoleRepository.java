package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
    @Query(value = """
                SELECT role_name FROM roles
            """, nativeQuery = true)
    List<String> findAllReturnName();
}
