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
@Table(name = "Products")
public class Product {
    @Id
    @Column(name = "product_id", updatable = false)
    private String productId;
    @Column(name = "sku", unique = true, nullable = false)
    private String sku;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "material", nullable = false)
    private String material;
    @Column(name = "region", nullable = false)
    private String region;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "point", nullable = false)
    private Double point;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date_post", nullable = false)
    private LocalDate datePost;

    // product appearance
    @Column
    private String color;
    @Column
    private Double length;
    @Column
    private Double width;
    @Column
    private Double height;
    @Column
    private Double weight;

    // for prepare deleting product
    @Column(name = "delete_flag")
    private int deleteFlag = 0;
    @Column(name = "old_sku")
    private String oldSku;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Product() {
        this.productId = RandomAnyString.generateCounterIncrement("product-");
        this.datePost = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Product(String name, String material, String region, String brand, Double price, String description) {
        this.productId = RandomAnyString.generateCounterIncrement("product-");
        this.name = name;
        this.material = material;
        this.region = region;
        this.brand = brand;
        this.price = price;
        this.point = calculatePoint(price);
        this.description = description;
        this.datePost = LocalDate.now();
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public void generateSku(){
        if(name != null && region != null && material != null && color != null){
            this.sku = name.substring(0, 3) + region.substring(0, 2) + material.substring(0, 3) + color.substring(0, 3);
        }
    }

    private Double calculatePoint(double price){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.point = calculatePoint(price);
    }
}
