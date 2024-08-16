package com.haratres.SpringSecurity.business.dtos.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByUserIdCartProductWithTotalPriceResponse {

    private List<GetByUserIdCartProductResponse> getByUserIdCartProductResponseList;
    private BigDecimal totalPrice;

    public void setTotalPrice() {

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (GetByUserIdCartProductResponse getByUserIdCartProductResponse : getByUserIdCartProductResponseList) {
            totalPrice = totalPrice.add(getByUserIdCartProductResponse.getTotalPrice());
        }
        this.totalPrice = totalPrice;
    }
}
