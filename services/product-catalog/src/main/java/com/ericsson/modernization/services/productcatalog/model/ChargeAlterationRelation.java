package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ChargeAlterationRelation extends EntityBase {
    @ManyToOne
    private ProdOfferPriceCharge prodOfferPriceCharge;
    @ManyToOne
    private ProdOfferPriceAlteration prodOfferPriceAlteration;

    public ProdOfferPriceCharge getProdOfferPriceCharge() {
        return prodOfferPriceCharge;
    }

    public void setProdOfferPriceCharge(ProdOfferPriceCharge prodOfferPriceCharge) {
        this.prodOfferPriceCharge = prodOfferPriceCharge;
    }

    public ProdOfferPriceAlteration getProdOfferPriceAlteration() {
        return prodOfferPriceAlteration;
    }

    public void setProdOfferPriceAlteration(ProdOfferPriceAlteration prodOfferPriceAlteration) {
        this.prodOfferPriceAlteration = prodOfferPriceAlteration;
    }
}
