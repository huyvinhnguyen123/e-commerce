package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, String> {
    Optional<Type> findByTypeName(String typeName);
    @Query(value = """
                SELECT type_name FROM types
            """, nativeQuery = true)
    List<String> findAllReturnName();
}
