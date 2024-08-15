package com.haratres.SpringSecurity.business.dtos.product;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedProductResponse {

    private int productId;
    private int categoryId;
    private String productName;
    private BigDecimal price;
    private int stock;
    private String size;
    private String color;
}
