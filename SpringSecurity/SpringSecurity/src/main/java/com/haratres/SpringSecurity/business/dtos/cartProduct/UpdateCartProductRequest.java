package com.haratres.SpringSecurity.business.dtos.cartProduct;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartProductRequest {

    @NotNull
    @Min(value = 1)
    @Positive
    private int cartProductId;

    @NotNull
    @Min(value = 1)
    @Positive
    private int productId;


    @NotNull
    @Min(value = 1)
    @Positive
    private  int quantity;


    private BigDecimal totalPrice;
}
