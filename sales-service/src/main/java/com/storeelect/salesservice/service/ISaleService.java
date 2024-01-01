package com.storeelect.salesservice.service;

import com.storeelect.salesservice.dto.SaleAsDTO;
import com.storeelect.salesservice.model.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISaleService {

    public List<SaleAsDTO> findSales();
    public SaleAsDTO findSaleById(Long sale_id);
    public boolean createSale(Sale sale);

}
