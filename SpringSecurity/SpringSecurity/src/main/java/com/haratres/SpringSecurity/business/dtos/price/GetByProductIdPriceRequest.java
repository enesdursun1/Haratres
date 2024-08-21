package com.haratres.SpringSecurity.business.dtos.price;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByProductIdPriceRequest {

    @NotNull
    @Min(value = 1)
    @Positive
    private int productId;
}
