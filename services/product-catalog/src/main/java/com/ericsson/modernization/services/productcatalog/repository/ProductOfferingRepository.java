package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOfferingRepository extends JpaRepository<ProductOffering, Integer> {
    ProductOffering findByIdAndIsDeletedIsFalse(int id);
    List<ProductOffering> findAllByIsDeletedIsFalse();
    List<ProductOffering> findAllByProductOfferingTypeId(int productOfferingTypeId);
}
