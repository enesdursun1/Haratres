package com.haratres.SpringSecurity.business.abstracts;

import java.util.List;

import com.haratres.SpringSecurity.business.dtos.category.*;
import com.haratres.SpringSecurity.entities.concretes.Category;


public interface CategoryService {

	List<GetAllCategoryResponse> getAll();
	GetByIdCategoryResponse getById(GetByIdCategoryRequest getByIdCategoryRequest);
	CreatedCategoryResponse add(CreateCategoryRequest createCategoryRequest);
	UpdatedCategoryResponse update(UpdateCategoryRequest updateCategoryRequest);
	void delete(DeleteCategoryRequest deleteCategoryRequest);
}
