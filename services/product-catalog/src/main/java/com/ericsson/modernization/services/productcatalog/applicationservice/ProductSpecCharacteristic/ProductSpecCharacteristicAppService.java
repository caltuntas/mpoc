package com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecCharacteristic;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecCharacteristic.Request.ProductSpecCharacteristicCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecCharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecCharacteristicAppService {

    @Autowired
    private ProductSpecCharacteristicRepository productSpecCharacteristicRepository;

    public ProductSpecCharacteristic create(ProductSpecCharacteristicCreateRequest productSpecCharacteristicCreateRequest) {
        ProductSpecCharacteristic productSpecCharacteristic = new ProductSpecCharacteristic();
        productSpecCharacteristic.setName(productSpecCharacteristicCreateRequest.getName());
        productSpecCharacteristic.setDescription(productSpecCharacteristicCreateRequest.getDescription());
        productSpecCharacteristic.setValueType(productSpecCharacteristicCreateRequest.getValueType());

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForStartDate(productSpecCharacteristicCreateRequest.getValidForStartDate());
        validFor.setValidForEndDate(productSpecCharacteristicCreateRequest.getValidForEndDate());
        productSpecCharacteristic.setValidFor(validFor);

        return productSpecCharacteristicRepository.save(productSpecCharacteristic);
    }

    public void delete(int productSpecCharacteristicID){
        ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicRepository.findById(productSpecCharacteristicID).get();
        if(productSpecCharacteristic != null) {
            productSpecCharacteristic.setDeleted(true);
            productSpecCharacteristicRepository.save(productSpecCharacteristic);
        }
    }

    public ProductSpecCharacteristic findById(int id){
        return productSpecCharacteristicRepository.findByIdAndIsDeletedIsFalse(id);
    }

    public List<ProductSpecCharacteristic> findAll() {
        return productSpecCharacteristicRepository.findAllByIsDeletedIsFalse();
    }
}
