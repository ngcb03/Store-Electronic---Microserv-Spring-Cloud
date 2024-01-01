package com.storeelect.salesservice.controller;

import com.storeelect.salesservice.dto.SaleAsDTO;
import com.storeelect.salesservice.model.Sale;
import com.storeelect.salesservice.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sales")
public class SaleController {

    @Autowired
    private ISaleService service;

    @GetMapping("/")
    public ResponseEntity<List<SaleAsDTO>> findSales(){
        return new ResponseEntity<>(
                this.service.findSales(),
                HttpStatus.OK);
    }

    @GetMapping("/{sale_id}")
    public ResponseEntity<SaleAsDTO> findSales(@PathVariable("sale_id") Long sale_id){
        return new ResponseEntity<>(
                this.service.findSaleById(sale_id),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSale(
            @RequestBody Sale sale){
        if(this.service.createSale(sale)){
            return new ResponseEntity<>(
                    "¡The Sale has been created!",
                    HttpStatus.CREATED);
        } return null;
    }

}
