package com.e.commerce.application.domain.entities;

import com.e.commerce.application.domain.utils.common.RandomAnyString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name = "category_id", updatable = false)
    private String categoryId;
    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Category() {
        this.categoryId = RandomAnyString.generateCounterIncrement("category-");
        this.categoryName = "Uncategorized";
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Category(String categoryName) {
        this.categoryId = RandomAnyString.generateCounterIncrement("category-");
        this.categoryName = categoryName;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }
}
