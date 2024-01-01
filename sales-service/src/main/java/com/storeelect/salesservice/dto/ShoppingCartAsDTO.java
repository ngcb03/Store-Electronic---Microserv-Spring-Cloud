package com.storeelect.salesservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartAsDTO {

    private Long cart_id;
    private List<Product> listProducts;
    private Double total_price;

}
