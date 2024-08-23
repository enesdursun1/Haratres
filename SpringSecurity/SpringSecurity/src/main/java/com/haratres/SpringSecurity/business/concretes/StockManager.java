package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.abstracts.StockService;
import com.haratres.SpringSecurity.business.dtos.stock.*;
import com.haratres.SpringSecurity.business.rules.StockBusinessRules;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.dataAccess.abstracts.StockDal;
import com.haratres.SpringSecurity.entities.concretes.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StockManager implements StockService {

    @Autowired
    private StockDal stockDal;
    @Autowired
    private ModelMapperService  modelMapperService;
    @Autowired
    private StockBusinessRules stockBusinessRules;

    @Override
    public List<GetAllStockResponse> getAll() {

        List<Stock> stocks = stockDal.findAll();

        List<GetAllStockResponse> response = stocks.stream().map(stock ->
                this.modelMapperService.forResponse().map(stock, GetAllStockResponse.class)).toList();

        return response;

    }

    @Override
    public GetByIdStockResponse getById(GetByIdStockRequest getByIdStockRequest) {

       Optional<Stock> stock = stockDal.findById(getByIdStockRequest.getStockId());

       stockBusinessRules.stockShouldBeExistWhenSelected(stock);

       GetByIdStockResponse getByIdStockResponse = modelMapperService.forResponse().map(stock, GetByIdStockResponse.class);

        return getByIdStockResponse;
    }

    @Override
    public CreatedStockResponse add(CreateStockRequest createStockRequest) {

        Stock stock = this.modelMapperService.forRequest().map(createStockRequest, Stock.class);

        Stock createdStock = stockDal.save(stock);

        CreatedStockResponse response = this.modelMapperService.forResponse().map(createdStock, CreatedStockResponse.class);

        return response;
    }

    @Override
    public UpdatedStockResponse update(UpdateStockRequest updateStockRequest) {

        stockBusinessRules.stockShouldBeExistWhenSelected(stockDal.findById(updateStockRequest.getStockId()));

        Stock Stock = this.modelMapperService.forRequest().map(updateStockRequest, Stock.class);

        Stock updatedStock = stockDal.save(Stock);

        UpdatedStockResponse response = this.modelMapperService.forResponse().map(updatedStock, UpdatedStockResponse.class);

        return response;
    }

    @Override
    public void delete(DeleteStockRequest deleteStockRequest) {

        stockBusinessRules.stockShouldBeExistWhenSelected(stockDal.findById(deleteStockRequest.getStockId()));

        stockDal.deleteById(deleteStockRequest.getStockId());
    }

    @Override
    public GetByProductIdStockResponse getByProductId(GetByProductIdStockRequest getByProductIdStockRequest) {

        stockBusinessRules.isStockEmptyForProduct(getByProductIdStockRequest.getProductId());

        Stock stock =  stockDal.findByProduct_ProductId(getByProductIdStockRequest.getProductId());

        return this.modelMapperService.forResponse().map(stock, GetByProductIdStockResponse.class);
    }
}
