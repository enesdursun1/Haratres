package com.haratres.SpringSecurity.business.dtos.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    @NotNull
    @Min(value = 1)
    @Positive
    private int categoryId;

    @NotNull
    @NotBlank
    private String productName;

    private String productCode;

    @NotNull
    @Min(value = 1)
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private int stock;

    @NotNull
    @NotBlank
    private String size;

    @NotNull
    @NotBlank
    private String color;
}
