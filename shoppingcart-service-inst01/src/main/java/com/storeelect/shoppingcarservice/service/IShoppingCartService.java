package com.storeelect.shoppingcarservice.service;

import com.storeelect.shoppingcarservice.dto.ShoppingCartAsDTO;
import com.storeelect.shoppingcarservice.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IShoppingCartService {

    public List<ShoppingCartAsDTO> findShoppingCarts();
    public ShoppingCartAsDTO findShoppingCartById(Long cart_id);
    public boolean createShoppingCart(ShoppingCart shoppingCart);
    public boolean deleteShoppingCart(Long cart_id);

}
