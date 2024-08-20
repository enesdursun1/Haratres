package com.haratres.SpringSecurity.business.dtos.stock;

import com.haratres.SpringSecurity.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStockRequest {

    private int productId;

    private int stockQuantity;



}
