package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristicValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecCharacteristicValueRepository extends  JpaRepository<ProductSpecCharacteristicValue, Integer> {
    ProductSpecCharacteristicValue findById(int id);
    List<ProductSpecCharacteristicValue> findAll();
    List<ProductSpecCharacteristicValue> findByProductSpecCharacteristicEquals(ProductSpecCharacteristic productSpecCharacteristic);
    ProductSpecCharacteristicValue findByValueAndProductSpecCharacteristicEquals(String name, ProductSpecCharacteristic productSpecCharacteristic);
    //ProductSpecCharacteristicValue findByIdAndProductSpecCharacteristicEquals(ProductSpecCharacteristic productSpecCharacteristic);
}
