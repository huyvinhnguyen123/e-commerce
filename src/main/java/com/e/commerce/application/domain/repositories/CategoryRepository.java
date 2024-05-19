package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.Category;
import com.e.commerce.application.domain.projections.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "SELECT category_id, category_name FROM categories", nativeQuery = true)
    List<CategoryProjection> findAllCategories();

    @Query(value = "SELECT c.categoryName FROM Category c where c.categoryName=?1")
    String findByName(String categoryName);
}
