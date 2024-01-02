package com.storeelect.shoppingcarservice.service;

import com.storeelect.shoppingcarservice.dto.Product;
import com.storeelect.shoppingcarservice.dto.ShoppingCartAsDTO;
import com.storeelect.shoppingcarservice.model.ShoppingCart;
import com.storeelect.shoppingcarservice.repository.IProductApiClient;
import com.storeelect.shoppingcarservice.repository.IShoppingCartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    private IShoppingCartRepository repository;

    @Autowired
    private IProductApiClient productApi;

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallBackFindShoppingCart")
    @Retry(name = "products-service")
    public List<ShoppingCartAsDTO> findShoppingCarts() {
        List<ShoppingCart> listShoppingCart = this.repository.findAll();
        if(!listShoppingCart.isEmpty()){
            List<ShoppingCartAsDTO> listShoppingCartDTO = new ArrayList<>();
            for(ShoppingCart shoppingCart: listShoppingCart){
                listShoppingCartDTO.add(this.consumeConvertDTO(shoppingCart));
            } return listShoppingCartDTO;
        } return null;
    }

    private List<ShoppingCartAsDTO> fallBackFindShoppingCart(Throwable throwable){
        List<ShoppingCartAsDTO> listShoppingCart = new ArrayList<>();
        listShoppingCart.add(this.fallBackFindShoppingCartById(throwable));
        return listShoppingCart;
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallBackFindShoppingCartById")
    @Retry(name = "products-service")
    public ShoppingCartAsDTO findShoppingCartById(Long cart_id) {
        ShoppingCart shoppingCart = this.repository.findById(cart_id).orElse(null);
        if(shoppingCart != null){ return this.consumeConvertDTO(shoppingCart); }
        return null;
    }

    private ShoppingCartAsDTO consumeConvertDTO(ShoppingCart shoppingCart){
        ShoppingCartAsDTO shoppingCartAsDTO = new ShoppingCartAsDTO();
        shoppingCartAsDTO.setCart_id(shoppingCart.getCart_id());
        shoppingCartAsDTO.setListProducts(
                this.productApi.findByNames(shoppingCart.getList_products())
        );

        List<Product> listProducts = shoppingCartAsDTO.getListProducts();
        Double total_price = 0D;
        for(Product product: listProducts){
            total_price += product.getPrice();
        } shoppingCartAsDTO.setTotal_price(total_price);

        return shoppingCartAsDTO;
    }

    private ShoppingCartAsDTO fallBackFindShoppingCartById(Throwable throwable){
        return new ShoppingCartAsDTO(
                999999L,
                null,
                999999D
        );
    }

    @Override
    public boolean createShoppingCart(ShoppingCart shoppingCart) {
        try {
            this.repository.save(shoppingCart);
            return true;
        } catch (Exception e) {
            System.out.println("The Shopping Cart has not been saved - Error: "
                    + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteShoppingCart(Long cart_id) {
        try {
            this.repository.deleteById(cart_id);
            return true;
        } catch (Exception e) {
            System.out.println("The Shopping Cart has not been deleted - Error: "
                    + e.getMessage());
            return false;
        }
    }
}
