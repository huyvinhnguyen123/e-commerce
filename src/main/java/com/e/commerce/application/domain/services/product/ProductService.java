package com.e.commerce.application.domain.services.product;

import com.e.commerce.application.domain.dtos.product.ProductDataInput;
import com.e.commerce.application.domain.entities.Product;
import com.e.commerce.application.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductDataInput productDataInput){
        Product product = new Product();
        product.setName(productDataInput.getName());
        product.setMaterial(productDataInput.getMaterial());
        product.setRegion(productDataInput.getRegion());
        product.setBrand(productDataInput.getBrand());
        product.setPrice(productDataInput.getPrice());
        product.setDescription(productDataInput.getDescription());
        product.setColor(productDataInput.getColor());
        product.generateSku();
        product.setLength(productDataInput.getLength());
        product.setWidth(productDataInput.getWidth());
        product.setHeight(productDataInput.getHeight());
        product.setWeight(productDataInput.getWeight());

        productRepository.save(product);
    }

    public void updateProduct(String productId, ProductDataInput productDataInput){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(
                "Product with id " + productId + " does not exist"));
        product.setName(productDataInput.getName());
        product.setMaterial(productDataInput.getMaterial());
        product.setRegion(productDataInput.getRegion());
        product.setBrand(productDataInput.getBrand());
        product.setPrice(productDataInput.getPrice());
        product.setDescription(productDataInput.getDescription());
        product.setColor(productDataInput.getColor());
        product.generateSku();
        product.setLength(productDataInput.getLength());
        product.setWidth(productDataInput.getWidth());
        product.setHeight(productDataInput.getHeight());
        product.setWeight(productDataInput.getWeight());

        productRepository.save(product);
    }

    public void deleteProduct(String productId, ProductDataInput productDataInput) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(
                        "Product with id " + productId + " does not exist"));
        product.setOldSku(product.getSku());
        product.setSku("");
        product.setDeleteFlag(1);
        productRepository.save(product);
    }
}
