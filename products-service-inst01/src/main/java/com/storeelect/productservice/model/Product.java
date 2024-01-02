package com.storeelect.productservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long product_code;

    @Basic
    private String name;
    private String brand;
    private Double price;

}
