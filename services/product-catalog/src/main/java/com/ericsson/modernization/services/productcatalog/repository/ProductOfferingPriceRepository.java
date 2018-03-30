package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOfferingPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOfferingPriceRepository extends JpaRepository<ProductOfferingPrice, Integer> {
    List<ProductOfferingPrice> findAllByIsDeletedIsFalse();
}
