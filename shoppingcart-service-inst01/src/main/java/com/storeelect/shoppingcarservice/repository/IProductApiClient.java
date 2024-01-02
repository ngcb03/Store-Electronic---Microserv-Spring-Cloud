package com.storeelect.shoppingcarservice.repository;

import com.storeelect.shoppingcarservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient (name = "products-service")
public interface IProductApiClient {

    @PostMapping("/api/v1/products/find-by-names")
    List<Product> findByNames(@RequestBody List<String> listNamesProducts);

}
