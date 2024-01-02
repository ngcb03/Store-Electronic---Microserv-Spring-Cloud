package com.storeelect.shoppingcarservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCartAsDTO {

    private Long cart_id;
    private List<Product> listProducts;
    private Double total_price;

}
