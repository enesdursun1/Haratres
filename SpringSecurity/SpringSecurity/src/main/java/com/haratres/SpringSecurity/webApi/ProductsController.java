package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.abstracts.ProductService;
import com.haratres.SpringSecurity.business.dtos.product.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductResponse> getAll() {

        return productService.getAll();

    }
    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdProductResponse getById(@RequestBody @Valid GetByIdProductRequest getByIdProductRequest) {

        return productService.getById(getByIdProductRequest);

    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(@RequestBody @Valid CreateProductRequest createProductRequest) {

        return productService.add(createProductRequest);

    }

    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse update(@RequestBody @Valid UpdateProductRequest updateProductRequest) {

        return productService.update(updateProductRequest);

    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Valid  DeleteProductRequest deleteProductRequest) {

         productService.delete(deleteProductRequest);

    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public GetByNameOrCodeProductResponse search(@RequestParam String word) {

      return  productService.getByNameOrCode(word);

    }


}
