package com.storeelect.salesservice.repository;

import com.storeelect.salesservice.dto.ShoppingCartAsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "shoppingcart-service")
public interface IShoppingCartApiClient {
    @GetMapping("/api/v1/shoppingcart/{cart_id}")
    public ShoppingCartAsDTO findShoppingCartById(
            @PathVariable("cart_id") Long cart_id);

}
