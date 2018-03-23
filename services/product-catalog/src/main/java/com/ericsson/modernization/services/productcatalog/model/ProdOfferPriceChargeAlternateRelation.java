package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProdOfferPriceChargeAlternateRelation extends EntityBase {

    @ManyToOne
    private ProdOfferPriceCharge prodOfferPriceCharge;

    @ManyToOne
    private AlternateProdOfferPriceCharge alternateProdOfferPriceCharge;

    public ProdOfferPriceCharge getProdOfferPriceCharge() {
        return prodOfferPriceCharge;
    }

    public void setProdOfferPriceCharge(ProdOfferPriceCharge prodOfferPriceCharge) {
        this.prodOfferPriceCharge = prodOfferPriceCharge;
    }

    public AlternateProdOfferPriceCharge getAlternateProdOfferPriceCharge() {
        return alternateProdOfferPriceCharge;
    }

    public void setAlternateProdOfferPriceCharge(AlternateProdOfferPriceCharge alternateProdOfferPriceCharge) {
        this.alternateProdOfferPriceCharge = alternateProdOfferPriceCharge;
    }


}
