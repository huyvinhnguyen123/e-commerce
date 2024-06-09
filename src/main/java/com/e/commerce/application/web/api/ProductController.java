package com.e.commerce.application.web.api;

import com.e.commerce.application.domain.dtos.product.ProductDataInput;
import com.e.commerce.application.domain.entities.Product;
import com.e.commerce.application.domain.repositories.ProductRepository;
import com.e.commerce.application.domain.services.product.ProductService;
import com.e.commerce.application.web.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/admin/create-product")
    public ResponseEntity<ResponseDto<Object>> createProduct(@Valid @RequestBody ProductDataInput productDataInput){
        Map<String, String> responseData = new HashMap<>();
        try {
            productService.createProduct(productDataInput);
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

    @PutMapping("/admin/update-product")
    public ResponseEntity<ResponseDto<Object>> updateProduct(
            @Valid
            @RequestBody ProductDataInput productDataInput,
            @RequestParam("productId") String productId){
        Map<String, String> responseData = new HashMap<>();
        try {
            productService.updateProduct(productId, productDataInput);
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
