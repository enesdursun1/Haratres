package com.haratres.SpringSecurity.business.abstracts;

import java.util.List;

import com.haratres.SpringSecurity.business.dtos.category.CreatedCategoryResponse;
import com.haratres.SpringSecurity.business.dtos.category.GetByIdCategoryRequest;
import com.haratres.SpringSecurity.business.dtos.category.UpdateCategoryRequest;
import com.haratres.SpringSecurity.business.dtos.category.UpdatedCategoryResponse;
import com.haratres.SpringSecurity.business.dtos.product.*;
import com.haratres.SpringSecurity.core.business.pagging.PageInfo;
import com.haratres.SpringSecurity.entities.concretes.Product;


public interface ProductService {

	List<GetAllProductResponse> getAll(PageInfo pageInfo);
	GetByIdProductResponse getById(GetByIdProductRequest getByIdProductRequest);
	CreatedProductResponse add(CreateProductRequest createProductRequest);
	UpdatedProductResponse update(UpdateProductRequest updateProductRequest);
	void delete(DeleteProductRequest deleteProductRequest);
	GetByNameOrCodeProductResponse getByNameOrCode(String keyword);
	List<GetListBySortProductResponse> getListBySort(String field,String sortDirection);

}
