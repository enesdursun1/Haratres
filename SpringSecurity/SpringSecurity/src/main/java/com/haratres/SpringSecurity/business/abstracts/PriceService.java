package com.haratres.SpringSecurity.business.abstracts;

import com.haratres.SpringSecurity.business.dtos.price.*;
import com.haratres.SpringSecurity.business.dtos.product.*;

import java.util.List;

public interface PriceService {

    List<GetAllPriceResponse> getAll();
    GetByIdPriceResponse getById(GetByIdPriceRequest getByIdPriceRequest);
    CreatedPriceResponse add(CreatePriceRequest createPriceRequest);
    UpdatedPriceResponse update(UpdatePriceRequest updatePriceRequest);
    void delete(DeletePriceRequest deletePriceRequest);
    GetByProductIdPriceResponse getByProductId(GetByProductIdPriceRequest getByProductIdPriceRequest);

}
