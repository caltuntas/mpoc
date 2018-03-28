package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharUse;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecCharUseRepository extends  JpaRepository<ProductSpecCharUse, Integer> {

}
