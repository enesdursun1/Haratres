package com.haratres.SpringSecurity.business.dtos.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteByUserIdCartProductRequest {

    private int userId;
}
