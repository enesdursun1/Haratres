package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.abstracts.CategoryService;
import com.haratres.SpringSecurity.business.dtos.category.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCategoryResponse> getAll() {

        return categoryService.getAll();

    }
    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdCategoryResponse getById(@RequestBody @Valid  GetByIdCategoryRequest getByIdCategoryRequest) {

        return categoryService.getById(getByIdCategoryRequest);

    }


    @PostMapping("/add")
    public CreatedCategoryResponse add(@RequestBody @Valid  CreateCategoryRequest createCategoryRequest) {

        return  categoryService.add(createCategoryRequest);
    }


    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCategoryResponse update(@RequestBody @Valid  UpdateCategoryRequest updateCategoryRequest) {

        return categoryService.update(updateCategoryRequest);

    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Valid  DeleteCategoryRequest deleteCategoryRequest) {

        categoryService.delete(deleteCategoryRequest);

    }



}
