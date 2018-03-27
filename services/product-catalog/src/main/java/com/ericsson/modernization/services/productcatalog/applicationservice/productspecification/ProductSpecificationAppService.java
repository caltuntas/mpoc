package com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Request.ProductSpecificationCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Request.ProductSpecificationValueItemModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Response.ProdSpecCharListResponse;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Response.ProdSpecCharValueModel;
import com.ericsson.modernization.services.productcatalog.model.*;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecCharacteristicRepository;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecCharacteristicValueRepository;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Request.ProductSpecificationCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class ProductSpecificationAppService {
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private ProductSpecCharacteristicRepository characteristicRepository;
    @Autowired
    private ProductSpecCharacteristicValueRepository characteristicValueRepository;

    public void create(ProductSpecificationCreateRequest request) {

        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setName(request.name);
        productSpecification.setCode(request.code);
        productSpecification.setDescription(request.description);
        // productSpecification.setProductType(request.productType);
        //productSpecification.setStatus(request.status);
        productSpecification.setCreateUserDate(new Date());


        for (ProductSpecificationValueItemModel selectedChar : request.selectedCharacteristics) {
            ProductSpecCharacteristic characteristic = characteristicRepository.findByIdAndIsDeletedIsFalse(selectedChar.id);
            ProductSpecCharUse charuse = new ProductSpecCharUse();
            charuse.setProductSpecification(productSpecification);
            charuse.setProductSpecCharacteristic(characteristic);

            for (int selectedValue : selectedChar.selectedValueIds) {

                ProductSpecCharacteristicValue val = characteristicValueRepository.findById(selectedValue);
                ProdSpecCharValueUse valueUse = new ProdSpecCharValueUse();
                valueUse.setProductSpecCharacteristicValue(val);
                valueUse.setProductSpecCharUse(charuse);
                charuse.addProductSpecCharValueUse(valueUse);
            }

            productSpecification.addCharUse(charuse);
        }

        productSpecificationRepository.save(productSpecification);
    }

    public List<ProdSpecCharListResponse> getCharacteristics() {
        return characteristicRepository.findAllByIsDeletedIsFalse().stream().map(x -> new ProdSpecCharListResponse(x.getId(), x.getName(), x.getValueType(),
                x.getProductSpecCharacteristicValues().stream().map(y -> new ProdSpecCharValueModel(y.getId(), y.getValue())).collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

}
