package com.haratres.SpringSecurity.business.dtos.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCategoryRequest {

    @NotNull
    @Min(value = 1)
    @Positive
    int categoryId;
}
