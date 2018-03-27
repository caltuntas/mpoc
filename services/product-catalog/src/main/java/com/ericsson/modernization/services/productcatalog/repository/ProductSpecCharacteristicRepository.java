package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecCharacteristicRepository extends  JpaRepository<ProductSpecCharacteristic, Integer> {
    ProductSpecCharacteristic findByIdAndIsDeletedIsFalse(int id);
    List<ProductSpecCharacteristic> findAllByIsDeletedIsFalse();
}
