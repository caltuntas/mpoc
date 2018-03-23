package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;

@Entity
public class RecurringProdOfferPriceCharge extends ProdOfferPriceCharge {
    private String periodType;

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

}
