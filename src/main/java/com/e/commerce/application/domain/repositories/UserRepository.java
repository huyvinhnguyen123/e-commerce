package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.User;
import com.e.commerce.application.domain.repositories.object.UserSelect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByOldLoginId(String email);

    @Query(value = """
                   SELECT u.user_id, u.username, u.birthdate, u.email, u.date_create, r.role_name
                      FROM Users u
                      LEFT JOIN Roles r ON r.role_id = u.role_id
                      WHERE r.role_name = COALESCE(:role, '') OR r.role_name IS NULL
            """, nativeQuery = true)
    Page<UserSelect> findAllUsers(@Param("role") String role, Pageable pageable);

    @Query(value = """
                    SELECT u.user_id, u.username, u.birthdate, u.email, u.date_create
                      i.image_id, i.image_name, i.image_path,
                      r.role_name
                      FROM Users u
                      LEFT JOIN User_Images ui ON u.user_id = ui.user_id
                      LEFT JOIN Images i ON i.image_id = ui.image_id
                      LEFT JOIN Roles r ON r.role_id = u.role_id
                      WHERE r.role_name IS NULL
                      OR r.role_name=:role
            """, nativeQuery = true)
    Page<UserSelect> findAllUsersWithImage(@Param("role") String role, Pageable pageable);
}