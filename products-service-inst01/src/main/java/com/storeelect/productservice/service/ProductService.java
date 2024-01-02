package com.storeelect.productservice.service;

import com.storeelect.productservice.model.Product;
import com.storeelect.productservice.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> findProducts() {
        return this.repository.findAll();
    }

    @Override
    public Product findProductById(Long product_code) {
        return this.repository.findById(product_code).orElse(null);
    }

    @Override
    public List<Product> findByName(List<String> listNamesProducts){
        List<Product> listProducts = new ArrayList<>();
        for(String name_product: listNamesProducts){
            listProducts.add(this.repository.findByName(name_product));
        } return listProducts;
    }

    @Override
    public boolean createProduct(Product product) {
        try{
            this.repository.save(product);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
