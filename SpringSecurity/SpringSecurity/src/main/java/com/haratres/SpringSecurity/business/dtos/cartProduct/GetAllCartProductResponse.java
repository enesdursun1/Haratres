package com.haratres.SpringSecurity.business.dtos.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCartProductResponse {


    private int cartProductId;

    private int productId;

    private  int cartId;

    private  int quantity;

    private BigDecimal totalPrice;




}
