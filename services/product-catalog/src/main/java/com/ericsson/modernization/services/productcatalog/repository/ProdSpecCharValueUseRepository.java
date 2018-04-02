package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharUse;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristicValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdSpecCharValueUseRepository extends  JpaRepository<ProdSpecCharValueUse, Integer> {

    ProdSpecCharValueUse findByProductSpecCharacteristicValueAndProductSpecCharUse(ProductSpecCharacteristicValue productSpecCharacteristicValue, ProductSpecCharUse productSpecCharUse);
    ProdSpecCharValueUse findByIdAndIsDeletedIsFalse(int id);

}
