package com.haratres.SpringSecurity.business.dtos.cart;

import com.haratres.SpringSecurity.business.dtos.cartProduct.GetListByCartIdCartProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListByUserIdCartResponse {


    private List<GetListByCartIdCartProductResponse> cartProducts;
    private BigDecimal totalPrice;

    public GetListByUserIdCartResponse(List<GetListByCartIdCartProductResponse> cartProducts) {
        this.cartProducts = cartProducts;
    }




}
