package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.abstracts.CartProductService;
import com.haratres.SpringSecurity.business.dtos.cartProduct.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartProducts")
public class CartProductsController {

   @Autowired
    private CartProductService cartProductService;


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCartProductResponse> getAll() {

        return cartProductService.getAll();

    }

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdCartProductResponse getById(@RequestBody @Valid GetByIdCartProductRequest getByIdCartProductRequest) {

        return cartProductService.getById(getByIdCartProductRequest);

    }
    @PostMapping("/add")
    public CartProductResponse add(@RequestBody @Valid CreateCartProductRequest createCartProductRequest) {

        return  cartProductService.addOrUpdateCartProduct(createCartProductRequest);

    }
    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCartProductResponse update(@RequestBody @Valid UpdateCartProductRequest updateCartProductRequest) {

        return cartProductService.update(updateCartProductRequest);

    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Valid DeleteCartProductRequest deleteCartProductRequest) {

        cartProductService.delete(deleteCartProductRequest);

    }



}
