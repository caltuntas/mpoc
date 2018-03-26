package com.ericsson.modernization.services.productcatalog.applicationservice;

import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecCharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecCharacteristicAppService {

    @Autowired
    private ProductSpecCharacteristicRepository productSpecCharacteristicRepository;

    public List<ProductSpecCharacteristic> findAll() {
        return productSpecCharacteristicRepository.findAll();
    }
}
