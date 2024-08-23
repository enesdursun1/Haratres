package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.abstracts.CartService;
import com.haratres.SpringSecurity.business.dtos.cart.GetListByUserIdCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartsController {

    @Autowired
    private CartService cartService;


    @GetMapping("/getListByUserId")
    @ResponseStatus(HttpStatus.OK)
    public GetListByUserIdCartResponse getListByUserId() {

        return cartService.getListByUserId();

    }

    @DeleteMapping("/clearCartProducts")
    @ResponseStatus(HttpStatus.OK)
    public void clearCartProducts() {

        cartService.clearCartProducts();

    }

}
