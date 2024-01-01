package com.storeelect.salesservice.service;

import com.storeelect.salesservice.dto.SaleAsDTO;
import com.storeelect.salesservice.dto.ShoppingCartAsDTO;
import com.storeelect.salesservice.model.Sale;
import com.storeelect.salesservice.repository.ISaleRepository;
import com.storeelect.salesservice.repository.IShoppingCartApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository repository;

    @Autowired
    private IShoppingCartApiClient shoppingCartApi;

    @Override
    @CircuitBreaker(name = "shoppingcart-service", fallbackMethod = "fallbackFindSales")
    @Retry(name = "shoppingcart-service")
    public List<SaleAsDTO> findSales() {
        List<Sale> listSales = this.repository.findAll();
        if(!listSales.isEmpty()){
            List<SaleAsDTO> listSalesDTO = new ArrayList<>();
            for(Sale sale: listSales){
                listSalesDTO.add(this.consumeConvertDTO(sale));
            } return listSalesDTO;
        } return null;

    }

    private List<SaleAsDTO> fallbackFindSales(Throwable throwable){
        List<SaleAsDTO> listSaleAsDTO = new ArrayList<>();
        listSaleAsDTO.add(this.fallbackFindSaleById(throwable));
        return listSaleAsDTO;
    }

    @Override
    @CircuitBreaker(name = "shoppingcart-service", fallbackMethod = "fallbackFindSaleById")
    @Retry(name = "shoppingcart-service")
    public SaleAsDTO findSaleById(Long sale_id) {
        Sale sale = this.repository.findById(sale_id).orElse(null);
        if(sale != null){ return this.consumeConvertDTO(sale); }
        return null;
    }

    private SaleAsDTO consumeConvertDTO(Sale sale){
        SaleAsDTO saleAsDTO = new SaleAsDTO();
        saleAsDTO.setSale_id(sale.getSale_id());
        saleAsDTO.setSale_date(sale.getSale_date());

        ShoppingCartAsDTO shoppingCartAsDTO = shoppingCartApi.findShoppingCartById(
                sale.getShoppingcart_id()
        );

        saleAsDTO.setShoppingCartAsDTO(shoppingCartAsDTO);
        return saleAsDTO;
    }

    private SaleAsDTO fallbackFindSaleById(Throwable throwable){
        return new SaleAsDTO(
                0L,
                null,
                null
        );
    }

    @Override
    public boolean createSale(Sale sale) {
        try{
            this.repository.save(sale);
            return true;
        } catch (Exception e) {
            System.out.println("The Sale has not been saved - Error: "
                    + e.getMessage());
        } return false;
    }

}
