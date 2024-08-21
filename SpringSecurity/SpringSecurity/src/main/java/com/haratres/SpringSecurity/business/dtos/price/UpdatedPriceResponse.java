package com.haratres.SpringSecurity.business.dtos.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedPriceResponse {

    private int priceId;
    private int productId;
    private BigDecimal price;
    private String currency;


}
