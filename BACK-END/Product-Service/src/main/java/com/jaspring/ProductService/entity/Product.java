package com.jaspring.ProductService.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long productId;

    private String name;
    private String description;
    private double price;
    private String sku;
}
