package com.e.commerce.application.domain.repositories;

import com.e.commerce.application.domain.entities.Product;
import com.e.commerce.application.domain.repositories.object.ProductSelect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p.productId as productId, p.brand as brand, p.sku as sku, p.name as name, p.material as material, p.region as region, p.price as price, p.point as point, p.description as description, p.datePost as datePost, p.color as color, p.length as length, p.width as width, p.height as height, p.weight as weight FROM Product p")
    Page<ProductSelect> findAllProducts(Pageable pageable);
}
