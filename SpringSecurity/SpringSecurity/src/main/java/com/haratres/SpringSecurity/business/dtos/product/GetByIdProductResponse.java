package com.haratres.SpringSecurity.business.dtos.product;

import com.haratres.SpringSecurity.entities.concretes.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdProductResponse {

    private int productId;
    private int categoryId;
    private String productName;
    private BigDecimal price;
    private int stock;
    private String size;
    private String color;

}
