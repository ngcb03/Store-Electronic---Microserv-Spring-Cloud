package com.storeelect.salesservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table (name = "sales")
public class Sale {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long sale_id;

    @Temporal(TemporalType.DATE)
    private LocalDate sale_date;

    @Basic
    private Long shoppingcart_id;

}
