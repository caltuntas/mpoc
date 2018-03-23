package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;

@Entity
public class DiscountProdOfferPriceAlteration extends ProdOfferPriceAlteration {

    private String chargePeriod;
    private int chargePeriodFrom;
    private int chargePeriodTo;
    
    public String getChargePeriod() {
		// TODO Auto-generated method stub
		return chargePeriod;
	}
    
    public void setChargePeriod(String chargePeriod) {
        this.chargePeriod = chargePeriod;
    }
    
	public int getChargePeriodFrom() {
		return chargePeriodFrom;
	}
	
	public void setChargePeriodFrom(int chargePeriodFrom) {
        this.chargePeriodFrom = chargePeriodFrom;
    }
    
	public int getChargePeriodTo() {
		return chargePeriodTo;
	}
	
	public void setChargePeriodTo(int chargePeriodTo) {
        this.chargePeriodTo = chargePeriodTo;
    }
    
}
