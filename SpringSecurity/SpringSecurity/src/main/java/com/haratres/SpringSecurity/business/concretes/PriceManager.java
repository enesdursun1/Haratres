package com.haratres.SpringSecurity.business.concretes;

import com.haratres.SpringSecurity.business.abstracts.PriceService;
import com.haratres.SpringSecurity.business.dtos.price.*;
import com.haratres.SpringSecurity.business.rules.PriceBusinessRules;
import com.haratres.SpringSecurity.core.utilites.mapping.ModelMapperService;
import com.haratres.SpringSecurity.dataAccess.abstracts.PriceDal;
import com.haratres.SpringSecurity.entities.concretes.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceManager implements PriceService {

    @Autowired
    private PriceDal priceDal;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private PriceBusinessRules priceBusinessRules;


    @Override
    public List<GetAllPriceResponse> getAll() {

        List<Price> priceList = priceDal.findAll();
        List<GetAllPriceResponse> response = priceList.stream().map(price ->
              this.modelMapperService.forResponse().map(price, GetAllPriceResponse.class)).toList();

        return response;
    }

    @Override
    public GetByIdPriceResponse getById(GetByIdPriceRequest getByIdPriceRequest) {

        Optional<Price> price = priceDal.findById(getByIdPriceRequest.getPriceId());

        priceBusinessRules.priceShouldBeExistWhenSelected(price);

        GetByIdPriceResponse response = this.modelMapperService.forResponse().map(price, GetByIdPriceResponse.class);
        return response;

    }

    @Override
    public CreatedPriceResponse add(CreatePriceRequest createPriceRequest) {

        Price price = this.modelMapperService.forRequest().map(createPriceRequest, Price.class);
        Price createdPrice = priceDal.save(price);
        CreatedPriceResponse response = this.modelMapperService.forResponse().map(createdPrice, CreatedPriceResponse.class);

        return response;
    }

    @Override
    public UpdatedPriceResponse update(UpdatePriceRequest updatePriceRequest) {

        priceBusinessRules.priceShouldBeExistWhenSelected(priceDal.findById(updatePriceRequest.getPriceId()));

        Price price = this.modelMapperService.forRequest().map(updatePriceRequest, Price.class);
        Price updatedPrice = priceDal.save(price);
        UpdatedPriceResponse response = this.modelMapperService.forResponse().map(updatedPrice, UpdatedPriceResponse.class);

        return response;
    }


    @Override
    public void delete(DeletePriceRequest deletePriceRequest) {

        priceBusinessRules.priceShouldBeExistWhenSelected(priceDal.findById(deletePriceRequest.getPriceId()));

        priceDal.deleteById(deletePriceRequest.getPriceId());
    }

    @Override
    public GetByProductIdPriceResponse getByProductId(GetByProductIdPriceRequest getByProductIdPriceRequest) {

        priceBusinessRules.isPriceEmptyForProduct(getByProductIdPriceRequest.getProductId());

        Price price =  priceDal.findByProduct_ProductId(getByProductIdPriceRequest.getProductId());
        return this.modelMapperService.forResponse().map(price, GetByProductIdPriceResponse.class);
    }
}
