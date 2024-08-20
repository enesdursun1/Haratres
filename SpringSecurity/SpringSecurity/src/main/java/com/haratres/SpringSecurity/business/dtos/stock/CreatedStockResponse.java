package com.haratres.SpringSecurity.business.dtos.stock;

import com.haratres.SpringSecurity.entities.concretes.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedStockResponse {

    private int stockId;

    private int productId;

    private int stockQuantity;
}
