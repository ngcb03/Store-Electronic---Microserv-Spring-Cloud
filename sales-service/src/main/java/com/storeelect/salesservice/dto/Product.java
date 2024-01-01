package com.storeelect.salesservice.dto;

import lombok.Data;

@Data
public class Product {

    private Long product_code;
    private String name;
    private String brand;
    private Double price;

}
