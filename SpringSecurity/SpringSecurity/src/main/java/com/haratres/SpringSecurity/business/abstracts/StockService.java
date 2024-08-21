package com.haratres.SpringSecurity.business.abstracts;

import com.haratres.SpringSecurity.business.dtos.stock.*;

import java.util.List;

public interface StockService {

    List<GetAllStockResponse> getAll();
    GetByIdStockResponse getById(GetByIdStockRequest getByIdStockRequest);
    CreatedStockResponse add(CreateStockRequest createStockRequest);
    UpdatedStockResponse update(UpdateStockRequest updateStockRequest);
    void delete(DeleteStockRequest deleteStockRequest);
    GetByProductIdStockResponse getByProductId(GetByProductIdStockRequest getByProductIdStockRequest);
}
