package com.haratres.SpringSecurity.business.dtos.category;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {


    @NotNull
    @Min(value = 1)
    @Positive
    private int categoryId;

    @NotBlank
    @NotNull
    private String categoryName;
}
