package com.storeelect.productservice.service;

import com.storeelect.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {

    public List<Product> findProducts();
    public Product findProductById(Long product_code);
    public List<Product> findByName(List<String> listNamesProducts);
    public boolean createProduct(Product product);

}
