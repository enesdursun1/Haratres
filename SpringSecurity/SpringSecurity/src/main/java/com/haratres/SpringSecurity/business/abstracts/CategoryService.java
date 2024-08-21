package com.haratres.SpringSecurity.business.abstracts;

import java.util.List;

import com.haratres.SpringSecurity.business.dtos.category.*;
import com.haratres.SpringSecurity.core.business.pagging.PageInfo;
import com.haratres.SpringSecurity.core.business.pagging.PaginateResponse;
import com.haratres.SpringSecurity.entities.concretes.Category;


public interface CategoryService {

	PaginateResponse<GetAllCategoryResponse> getAll(PageInfo pageInfo);
	GetByIdCategoryResponse getById(GetByIdCategoryRequest getByIdCategoryRequest);
	CreatedCategoryResponse add(CreateCategoryRequest createCategoryRequest);
	UpdatedCategoryResponse update(UpdateCategoryRequest updateCategoryRequest);
	void delete(DeleteCategoryRequest deleteCategoryRequest);
}
