package com.haratres.SpringSecurity.business.dtos.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCartProductResponse implements CartProductResponse {

    private int cartProductId;

    private int productId;

    private  int userId;

    private  int quantity;

    private BigDecimal totalPrice;
}
