package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "SELECT categoryId, categoryName FROM Category")
    List<Category> findAllCategories();

    @Query(value = "SELECT c.categoryName FROM Category c where c.categoryName=?1")
    String findByName(String categoryName);
}
