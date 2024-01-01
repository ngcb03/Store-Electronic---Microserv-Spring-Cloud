package com.storeelect.salesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleAsDTO {

    private Long sale_id;
    private LocalDate sale_date;
    private ShoppingCartAsDTO shoppingCartAsDTO;

}
