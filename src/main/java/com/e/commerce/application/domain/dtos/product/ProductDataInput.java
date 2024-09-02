package com.e.commerce.application.domain.dtos.product;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDataInput {
    private String name;
    private String material;
    private String region;
    private String brand;
    private Double price;
    private String description;
    private String color;
    private Double length;
    private Double width;
    private Double height;
    private Double weight;
}
