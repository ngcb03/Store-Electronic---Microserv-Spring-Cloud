package com.storeelect.shoppingcarservice.controller;

import com.storeelect.shoppingcarservice.dto.ShoppingCartAsDTO;
import com.storeelect.shoppingcarservice.model.ShoppingCart;
import com.storeelect.shoppingcarservice.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService service;

    @Value("${server.port}")
    private int server_port;

    @GetMapping("/")
    public ResponseEntity<List<ShoppingCartAsDTO>> findShoppingCarts(){
        return new ResponseEntity<>(
                this.service.findShoppingCarts(),
                HttpStatus.OK);
    }

    @GetMapping("/{cart_id}")
    public ResponseEntity<ShoppingCartAsDTO> findShoppingCartById(
            @PathVariable("cart_id") Long cart_id){
        System.out.println("------------- I´m in the port " + server_port);
        return new ResponseEntity<>(
                this.service.findShoppingCartById(cart_id),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createShoppingCart(
            @RequestBody ShoppingCart shoppingCart){
        if(this.service.createShoppingCart(shoppingCart)){
            return new ResponseEntity<>(
                    "¡The Shopping Cart has been created!",
                    HttpStatus.CREATED);
        } return null;
    }

    @DeleteMapping("/delete/{cart_id}")
    public ResponseEntity<String> deleteShoppingCart(
            @PathVariable("cart_id") Long cart_id){
        if(this.service.deleteShoppingCart(cart_id)){
            return new ResponseEntity<>(
                    "¡The Shopping Cart has been deleted!",
                    HttpStatus.OK);
        } return null;
    }

}
