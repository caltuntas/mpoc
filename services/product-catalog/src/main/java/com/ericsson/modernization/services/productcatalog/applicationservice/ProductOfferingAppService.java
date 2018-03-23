package com.ericsson.modernization.services.productcatalog.applicationservice;

import com.ericsson.modernization.services.productcatalog.applicationservice.request.ProductOfferingCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.Duration;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOfferingAppService {

    @Autowired
    private ProductOfferingRepository productOfferingRepository;

    public void create(ProductOfferingCreateRequest productOfferingCreateRequest){

        ProductOffering productOffering = new ProductOffering();
        productOffering.setName(productOfferingCreateRequest.getName());
        productOffering.setIsSellable(productOfferingCreateRequest.getSellable());
        productOffering.setDescription(productOfferingCreateRequest.getDescription());
        productOffering.setExternalId(productOffering.getExternalId());
        productOffering.setIsReplicated(productOfferingCreateRequest.getReplicated());

        Duration returnPeriod = new Duration();
        returnPeriod.setPeriodValue(productOfferingCreateRequest.getReturnPeriodValue());
        returnPeriod.setPeriodUnit(productOfferingCreateRequest.getReturnPeriodUnit());
        productOffering.setReturnPeriod(returnPeriod);

        Duration warrantyPeriod = new Duration();
        warrantyPeriod.setPeriodValue(productOfferingCreateRequest.getWarrantyPeriodValue());
        warrantyPeriod.setPeriodUnit(productOfferingCreateRequest.getWarrantyPeriodUnit());
        productOffering.setWarrantyPeriod(warrantyPeriod);

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(productOfferingCreateRequest.getValidForEndDate());
        validFor.setValidForStartDate(productOfferingCreateRequest.getValidForStartDate());
        productOffering.setValidFor(validFor);

        ProductSpecification productSpecification =  null; //TODO: servisler yazılınca get edilecek
        productOffering.setProductSpecification(productSpecification);

        productOfferingRepository.save(productOffering);
    }

    public void delete(int productOfferingID){
        ProductOffering productOffering = productOfferingRepository.findById(productOfferingID).get();
        if(productOffering != null){
            productOffering.setDeleted(true);
            productOfferingRepository.save(productOffering);
        }
    }

    public ProductOffering findById(int id){
        return productOfferingRepository.findByIdAndIsDeletedIsFalse(id);
    }

    public List<ProductOffering> findAll(){
        return productOfferingRepository.findAllByIsDeletedIsFalse();
    }
}
