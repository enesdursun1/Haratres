package com.haratres.SpringSecurity.webApi;

import com.haratres.SpringSecurity.business.abstracts.PriceService;
import com.haratres.SpringSecurity.business.abstracts.StockService;
import com.haratres.SpringSecurity.business.dtos.price.*;
import com.haratres.SpringSecurity.business.dtos.stock.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StocksController {

    @Autowired
    private StockService stockService;


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllStockResponse> getAll() {

        return stockService.getAll();

    }
    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdStockResponse getById(@RequestBody @Valid GetByIdStockRequest getByIdStockRequest) {

        return stockService.getById(getByIdStockRequest);

    }

    @GetMapping("/getByProductId")
    @ResponseStatus(HttpStatus.OK)
    public GetByProductIdStockResponse getByProductId(@RequestBody @Valid GetByProductIdStockRequest getByProductIdStockRequest) {

        return stockService.getByProductId(getByProductIdStockRequest);

    }

    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedStockResponse update(@RequestBody @Valid UpdateStockRequest updateStockRequest) {

        return stockService.update(updateStockRequest);

    }

}
