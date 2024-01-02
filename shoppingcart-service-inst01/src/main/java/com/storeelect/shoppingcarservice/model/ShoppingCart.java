package com.storeelect.shoppingcarservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @ElementCollection (fetch =  FetchType.EAGER)
    private List<String> list_products;

    @Basic
    private Double total_price;

}
