package com.haratres.SpringSecurity.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.haratres.SpringSecurity.business.abstracts.CategoryService;
import com.haratres.SpringSecurity.business.dtos.category.*;
import com.haratres.SpringSecurity.business.dtos.product.GetAllProductResponse;
import com.haratres.SpringSecurity.business.rules.CategoryBusinessRules;
import com.haratres.SpringSecurity.core.business.pagging.PageInfo;
import com.haratres.SpringSecurity.core.business.pagging.PaginateResponse;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.haratres.SpringSecurity.dataAccess.abstracts.CategoryDal;


@Service
public class CategoryManager implements CategoryService {

	
	@Autowired
	private CategoryDal categoryDal;
	@Autowired
	private ModelMapperService modelMapperService;
	@Autowired
	private CategoryBusinessRules categoryBusinessRules;


	@Override
	public PaginateResponse<GetAllCategoryResponse> getAll(PageInfo pageInfo) {

		List<Category> categories = categoryBusinessRules.paginationProcess(pageInfo);

		List<GetAllCategoryResponse> response = categories.stream().map(
				category -> this.modelMapperService.forResponse().map(category, GetAllCategoryResponse.class)).toList();

		PaginateResponse<GetAllCategoryResponse> paginateResponse =
				new PaginateResponse(pageInfo.getPageIndex(), pageInfo.getPageSize(), categoryDal.count() ,response);

		return paginateResponse;
	}

	@Override
	public GetByIdCategoryResponse getById(GetByIdCategoryRequest getByIdCategoryRequest) {

		categoryBusinessRules.categoryShouldBeExistWhenSelected(getByIdCategoryRequest.getCategoryId());

		Category category = categoryDal.findById(getByIdCategoryRequest.getCategoryId()).get();
		GetByIdCategoryResponse response = this.modelMapperService.forResponse().map(category, GetByIdCategoryResponse.class);

		return response;
	}

	@Override
	public CreatedCategoryResponse add(CreateCategoryRequest createCategoryRequest) {

		categoryBusinessRules.categoryNameCanNotBeDuplicatedWhenInserted(createCategoryRequest.getCategoryName());

		Category category = modelMapperService.forRequest().map(createCategoryRequest, Category.class);
		Category createdCategory = categoryDal.save(category);
		CreatedCategoryResponse response = this.modelMapperService.forResponse().map(createdCategory, CreatedCategoryResponse.class);

		return response;
	}

	@Override
	public UpdatedCategoryResponse update(UpdateCategoryRequest updateCategoryRequest) {

		categoryBusinessRules.categoryShouldBeExistWhenSelected(updateCategoryRequest.getCategoryId());
		categoryBusinessRules.categoryNameCanNotBeDuplicatedWhenUpdated(updateCategoryRequest.getCategoryName(),updateCategoryRequest.getCategoryId());

		Category category = modelMapperService.forRequest().map(updateCategoryRequest, Category.class);
		Category updatedCategory = categoryDal.save(category);
		UpdatedCategoryResponse response = this.modelMapperService.forResponse().map(updatedCategory, UpdatedCategoryResponse.class);
	    return response;
	}


	@Override
	public void delete(DeleteCategoryRequest deleteCategoryRequest) {

		categoryBusinessRules.categoryShouldBeExistWhenSelected(deleteCategoryRequest.getCategoryId());

		categoryDal.deleteById(deleteCategoryRequest.getCategoryId());
	}
}
