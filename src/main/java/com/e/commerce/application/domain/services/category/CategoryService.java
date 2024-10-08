package com.e.commerce.application.domain.services.category;

import com.e.commerce.application.domain.dtos.category.CategoryReturnDTO;
import com.e.commerce.application.domain.dtos.category.CategoryInput;
import com.e.commerce.application.domain.entities.Category;
import com.e.commerce.application.domain.projections.CategoryProjection;
import com.e.commerce.application.domain.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryInput categoryInput) {
        Category category = new Category();
        category.setCategoryName(categoryInput.getName());
        categoryRepository.save(category);
    }

    public void updateCategory(String categoryId, CategoryInput categoryInput) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException(
                        "Category with id " + categoryId + "does not exist"));
        category.setCategoryName(categoryInput.getName());
        categoryRepository.save(category);
    }

    public void deleteCategory(String categoryId) {
        boolean categoryExists = categoryRepository.existsById(categoryId);

        if(!categoryExists){
            throw new IllegalStateException("Category with id " + categoryId + " does not exit");
        }
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryReturnDTO> findAllCategories() {
        List<CategoryProjection> categories = categoryRepository.findAllCategories();
        List<CategoryReturnDTO> categoryDTOs = new ArrayList<>();

        for (CategoryProjection c : categories){
            CategoryReturnDTO categoryReturnDTO = new CategoryReturnDTO();
            categoryReturnDTO.setCategoryId(c.getCategory_id());
            categoryReturnDTO.setCategoryName(c.getCategory_name());

            categoryDTOs.add(categoryReturnDTO);
        }
        return categoryDTOs;
    }

    public String findOneCategory(String categoryName) {return categoryRepository.findByName(categoryName);}
}
