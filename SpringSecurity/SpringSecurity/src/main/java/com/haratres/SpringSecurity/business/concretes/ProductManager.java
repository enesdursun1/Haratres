package com.haratres.SpringSecurity.business.concretes;

import java.util.List;
import java.util.Optional;

import com.haratres.SpringSecurity.business.abstracts.ProductService;
import com.haratres.SpringSecurity.business.dtos.product.*;
import com.haratres.SpringSecurity.business.rules.ProductBusinessRules;
import com.haratres.SpringSecurity.business.utilities.BarcodeGenerator;
import com.haratres.SpringSecurity.core.business.pagging.PageInfo;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.entities.concretes.Price;
import com.haratres.SpringSecurity.entities.concretes.Product;
import com.haratres.SpringSecurity.entities.concretes.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.haratres.SpringSecurity.dataAccess.abstracts.ProductDal;

@Service
public class ProductManager implements ProductService {

	@Autowired
	private ProductDal productDal;
	@Autowired
	private ModelMapperService modelMapperService;
	@Autowired
	private ProductBusinessRules productBusinessRules;


	@Override
	public List<GetAllProductResponse> getAll() {

		//PageRequest pageRequest = PageRequest.of(pageInfo.getPageIndex(), pageInfo.getPageSize());
		//products = productDal.findAll(pageRequest).getContent();

		List<Product> products = productDal.findAll();
		List<GetAllProductResponse> response = products.stream().map(
				product -> this.modelMapperService.forResponse().map(product, GetAllProductResponse.class)).toList();

		return response;
	}

	@Override
	public GetByIdProductResponse getById(GetByIdProductRequest getByIdProductRequest) {

		Optional<Product> productCheck = productDal.findById(getByIdProductRequest.getProductId());

		productBusinessRules.productShouldBeExistWhenSelected(productCheck);

		Product product = productDal.findById(getByIdProductRequest.getProductId()).get();
		GetByIdProductResponse response = this.modelMapperService.forResponse().map(product, GetByIdProductResponse.class);

		return response;
	}

	@Override
	public CreatedProductResponse add(CreateProductRequest createProductRequest) {

		productBusinessRules.productNameCanNotBeDuplicatedWhenInserted(createProductRequest.getProductName());
        productBusinessRules.categoryShouldBeExistWhenSelected(createProductRequest.getCategoryId());

		String productCode = BarcodeGenerator.barcodeGenerator();

		productCode = productBusinessRules.productCodeCanNotBeDuplicatedWhenInserted(productCode);

		createProductRequest.setProductCode(productCode);

		Product product = modelMapperService.forRequest().map(createProductRequest, Product.class);

		product.setProductId(0); //categoryId parametresini productId olarak aldığı için 0 değeri atandı.

		product.setPrice(new Price(createProductRequest.getPrice(),"TL",product));
		product.setStock(new Stock(createProductRequest.getStock(),product));

		Product createdProduct = productDal.save(product);
		CreatedProductResponse response = this.modelMapperService.forResponse().map(createdProduct, CreatedProductResponse.class);

		return response;
	}

	@Override
	public UpdatedProductResponse update(UpdateProductRequest updateProductRequest) {

		Optional<Product> productCheck = productDal.findById(updateProductRequest.getProductId());

		productBusinessRules.productShouldBeExistWhenSelected(productCheck);
		productBusinessRules.productNameCanNotBeDuplicatedWhenUpdated(updateProductRequest.getProductName(), updateProductRequest.getProductId());
		productBusinessRules.categoryShouldBeExistWhenSelected(updateProductRequest.getCategoryId());

        updateProductRequest.setProductCode(productCheck.get().getProductCode());

		Product product = modelMapperService.forRequest().map(updateProductRequest, Product.class);

		product.getPrice().setPriceId(productCheck.get().getPrice().getPriceId());
		product.getPrice().setPrice(updateProductRequest.getPrice());
		product.getPrice().setCurrency(productCheck.get().getPrice().getCurrency());
		product.getPrice().setProduct(product);

		product.getStock().setStockId(productCheck.get().getStock().getStockId());
		product.getStock().setStockQuantity(updateProductRequest.getStock());
		product.getStock().setProduct(product);

		Product updatedProduct = productDal.save(product);
		UpdatedProductResponse response = this.modelMapperService.forResponse().map(updatedProduct, UpdatedProductResponse.class);
		return response;
	}


	@Override
	public void delete(DeleteProductRequest deleteProductRequest) {

		Optional<Product> productCheck = productDal.findById(deleteProductRequest.getProductId());

		productBusinessRules.productShouldBeExistWhenSelected(productCheck);

        productDal.deleteById(deleteProductRequest.getProductId());
	}

	@Override
	public GetByNameOrCodeProductResponse getByNameOrCode(String keyword) {

		keyword = keyword.toLowerCase();

		Product product = productDal.findByProductNameOrProductCode(keyword);

		productBusinessRules.productShouldBeExistWhenSearch(product);

        GetByNameOrCodeProductResponse response = this.modelMapperService.forResponse().map(product, GetByNameOrCodeProductResponse.class);

		return response;
	}

	@Override
	public List<GetListBySortProductResponse> getListBySort(String field, String sortDirection) {



		productBusinessRules.validateSortField(field);
		productBusinessRules.validateSortDirection(sortDirection);

		if (field.equals("price")) field = "price.price";
		if (field.equals("stock")) field = "stock.stockQuantity";

		Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), field);

		List<Product> products = productDal.findAll(sort);

		List<GetListBySortProductResponse> response = products.stream().map(product ->
				this.modelMapperService.forResponse().map(product, GetListBySortProductResponse.class)).toList();


		return response;
	}


}
