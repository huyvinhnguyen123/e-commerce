package com.e.commerce.application.web.api;

import com.e.commerce.application.domain.dtos.category.CategoryReturnDTO;
import com.e.commerce.application.domain.dtos.category.CategoryInput;
import com.e.commerce.application.domain.entities.Category;
import com.e.commerce.application.domain.repositories.CategoryRepository;
import com.e.commerce.application.domain.services.category.CategoryService;
import com.e.commerce.application.web.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/admin/create-category")
    public ResponseEntity<ResponseDto<Object>> createCategory(@Valid @RequestBody CategoryInput categoryInput) {
        String categoryName = categoryInput.getName();
        String existingCategoryName = categoryService.findOneCategory(categoryName);
        Map<String, String> responseData = new HashMap<>();
        if(categoryName.isEmpty() || existingCategoryName != null && existingCategoryName.equals(categoryName)){
            responseData.put("error", "create fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.build()
                            .withHttpStatus(HttpStatus.BAD_REQUEST)
                            .withMessage("Bad request")
                            .withData(responseData));
        }else{
            try {
                categoryService.createCategory(categoryInput);
                return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
            }catch (Exception e){
                responseData.put("error", "Internal Server Error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseDto.build()
                                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                .withMessage("Internal Server Error")
                                .withData(responseData));
            }
        }
    }

    @PutMapping("/admin/update-category")
    public ResponseEntity<ResponseDto<Object>> updateCategory(@RequestParam("categoryId") String categoryId, @RequestBody CategoryInput categoryInput){
        Map<String, String> responseData = new HashMap<>();
        if(categoryInput.getName().isEmpty()){
            responseData.put("error", "update fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.build()
                            .withHttpStatus(HttpStatus.BAD_REQUEST)
                            .withMessage("Bad request")
                            .withData(responseData));
        }else{
            try {
                categoryService.updateCategory(categoryId, categoryInput);
                return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
            }catch (Exception e){
                responseData.put("error", "Internal Server Error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseDto.build()
                                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                .withMessage("Internal Server Error")
                                .withData(responseData));
            }
        }
    }

    @DeleteMapping("/admin/delete-category")
    public ResponseEntity<ResponseDto<Object>> deleteCategory(@RequestParam("categoryId") String categoryId){
        Optional<Category> existingCategoryId = categoryRepository.findById(categoryId);
        Map<String, String> responseData = new HashMap<>();
        if(existingCategoryId.isEmpty()){
            responseData.put("error", "delete fail");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.build()
                            .withHttpStatus(HttpStatus.BAD_REQUEST)
                            .withMessage("Bad request")
                            .withData(responseData));
        }else{
            try {
                categoryService.deleteCategory(categoryId);
                return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
            }catch (Exception e){
                responseData.put("error", "Internal Server Error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseDto.build()
                                .withHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                .withMessage("Internal Server Error")
                                .withData(responseData));
            }
        }
    }

    @GetMapping("/categories")
    public List<CategoryReturnDTO> findAllCategories(){return categoryService.findAllCategories();}
}
