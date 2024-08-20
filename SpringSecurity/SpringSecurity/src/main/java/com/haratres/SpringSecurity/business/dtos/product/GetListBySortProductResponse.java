package com.haratres.SpringSecurity.business.dtos.product;

import com.haratres.SpringSecurity.entities.concretes.Price;
import com.haratres.SpringSecurity.entities.concretes.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListBySortProductResponse {

    private int productId;
    private int categoryId;
    private String productName;
    private String productCode;
    private Price price;
    private Stock stock;
    private String size;
    private String color;
}
