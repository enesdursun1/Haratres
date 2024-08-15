package com.haratres.SpringSecurity.business.dtos.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdProductRequest {

    @NotNull
    @Min(value = 1)
    @Positive
    int productId;
}
