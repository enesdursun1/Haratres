package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.abstracts.PriceService;
import com.haratres.SpringSecurity.business.dtos.price.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PricesController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllPriceResponse> getAll() {

        return priceService.getAll();

    }
    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdPriceResponse getById(@RequestBody @Valid  GetByIdPriceRequest getByIdPriceRequest) {

        return priceService.getById(getByIdPriceRequest);

    }

    @GetMapping("/getByProductId")
    @ResponseStatus(HttpStatus.OK)
    public GetByProductIdPriceResponse getByProductId(@RequestBody @Valid  GetByProductIdPriceRequest getByProductIdPriceRequest) {

        return priceService.getByProductId(getByProductIdPriceRequest);

    }

    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedPriceResponse update(@RequestBody @Valid UpdatePriceRequest updatePriceRequest) {

        return priceService.update(updatePriceRequest);

    }


}
