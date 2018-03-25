package com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Request.ProductSpecificationCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductSpecificationAppService {
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;


    public void create(ProductSpecificationCreateRequest request){

        ProductSpecification productSpecification = new ProductSpecification();


    }

}
