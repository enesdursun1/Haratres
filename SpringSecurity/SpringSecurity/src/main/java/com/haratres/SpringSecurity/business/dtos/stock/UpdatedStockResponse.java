package com.haratres.SpringSecurity.business.dtos.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedStockResponse {

    private int stockId;

    private int productId;

    private int stockQuantity;
}
