package com.haratres.SpringSecurity.business.dtos.cartProduct;

import com.haratres.SpringSecurity.entities.concretes.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCartProductResponse implements CartProductResponse {

    private int cartProductId;

    private int productId;

   // private  int cartId;

    private  int quantity;

    private BigDecimal totalPrice;
}
