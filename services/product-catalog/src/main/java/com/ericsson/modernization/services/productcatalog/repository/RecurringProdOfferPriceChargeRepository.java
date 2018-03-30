package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.RecurringProdOfferPriceCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringProdOfferPriceChargeRepository extends JpaRepository<RecurringProdOfferPriceCharge, Integer> {
}
