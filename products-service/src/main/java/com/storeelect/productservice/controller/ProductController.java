package com.storeelect.productservice.controller;

import com.storeelect.productservice.model.Product;
import com.storeelect.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @Value("${server.port}")
    private int server_port;

    @GetMapping("/")
    public ResponseEntity<List<Product>> findProducts(){
        return new ResponseEntity<>(
                this.service.findProducts(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{product_code}")
    public ResponseEntity<Product> findProductByCode(
            @PathVariable("product_code") Long product_code){
        return new ResponseEntity<>(
                this.service.findProductById(product_code),
                HttpStatus.OK
        );
    }

    @PostMapping("/find-by-names")
    public ResponseEntity<List<Product>> findByNames(
            @RequestBody List<String> listNamesProducts){
        System.out.println("-------------- I´m in the port " + server_port);
        return new ResponseEntity<>(
                this.service.findByName(listNamesProducts),
                HttpStatus.OK
        );
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
            @RequestBody Product product){
        if(this.service.createProduct(product)){
            return new ResponseEntity<>(
                    "¡The Product has been created!",
                    HttpStatus.CREATED);
        } return null;
    }

}
