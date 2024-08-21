package com.haratres.SpringSecurity.business.dtos.price;

import com.haratres.SpringSecurity.entities.concretes.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedPriceResponse {

    private int priceId;
    private int productId;
    private BigDecimal price;
    private String currency;

}
