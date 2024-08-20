package com.haratres.SpringSecurity.business.dtos.price;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePriceRequest {

    @NotNull
    @Positive
    @Min(value=1)
    private int priceId;


    @NotNull
    @Min(value = 1)
    @Positive
    private BigDecimal price;

    @NotNull
    @NotBlank
    private String currency;

    @NotNull
    @Min(value = 1)
    @Positive
    private int productId;

}
