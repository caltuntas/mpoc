package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AlternateProdOfferPriceCharge extends ProdOfferPriceCharge {
    @ManyToOne
    private ProdOfferPriceCharge prodOfferPriceCharge;

    public ProdOfferPriceCharge getProdOfferPriceCharge() {
        return prodOfferPriceCharge;
    }

    public void setProdOfferPriceCharge(ProdOfferPriceCharge prodOfferPriceCharge) {
        this.prodOfferPriceCharge = prodOfferPriceCharge;
    }
}
