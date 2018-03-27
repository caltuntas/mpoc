package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering;

import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.ProductSpecificationAppService;
import com.ericsson.modernization.services.productcatalog.model.Duration;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductOfferingAppService {

    @Autowired
    private ProductOfferingRepository productOfferingRepository;
    @Autowired
    private ProductSpecificationAppService productSpecificationAppService;

    public ProductOffering create(ProductOfferingCreateRequest createRequest){

        ProductOffering productOffering = new ProductOffering();
        productOffering.setName(createRequest.getName());
        productOffering.setIsSellable(createRequest.getSellable());
        productOffering.setDescription(createRequest.getDescription());
        productOffering.setExternalId(productOffering.getExternalId());
        productOffering.setIsReplicated(createRequest.getReplicated());

        Duration returnPeriod = new Duration();
        returnPeriod.setPeriodValue(createRequest.getReturnPeriodValue());
        returnPeriod.setPeriodUnit(createRequest.getReturnPeriodUnit());
        productOffering.setReturnPeriod(returnPeriod);

        Duration warrantyPeriod = new Duration();
        warrantyPeriod.setPeriodValue(createRequest.getWarrantyPeriodValue());
        warrantyPeriod.setPeriodUnit(createRequest.getWarrantyPeriodUnit());
        productOffering.setWarrantyPeriod(warrantyPeriod);

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(createRequest.getValidForEndDate());
        validFor.setValidForStartDate(createRequest.getValidForStartDate());
        productOffering.setValidFor(validFor);

        ProductSpecification specification = productSpecificationAppService.findById(createRequest.getProductSpecificationId());
        productOffering.setProductSpecification(specification);

        return productOfferingRepository.save(productOffering);
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
