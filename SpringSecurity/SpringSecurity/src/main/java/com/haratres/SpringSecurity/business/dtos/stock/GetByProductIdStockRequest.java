package com.haratres.SpringSecurity.business.dtos.stock;

import com.haratres.SpringSecurity.entities.concretes.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByProductIdStockRequest {

    @NotNull
    @Positive
    @Min(value=1)
    private int productId;

}
