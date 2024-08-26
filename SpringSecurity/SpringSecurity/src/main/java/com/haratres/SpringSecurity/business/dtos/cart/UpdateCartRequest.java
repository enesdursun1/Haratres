package com.haratres.SpringSecurity.business.dtos.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartRequest {

    private int cartId;
    private int userId;
    private BigDecimal totalPrice;
}
