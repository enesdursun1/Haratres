package com.haratres.SpringSecurity.business.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCategoryResponse {

    private int categoryId;
    private String categoryName;

}
