package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductOfferingAdvertisedAs extends EntityBase {
    @ManyToOne
    private ProductOfferingPrice ProductOfferingPrice;
    @ManyToOne
    private ProdCatProdOffer ProdCatProdOffer;

    public com.ericsson.modernization.services.productcatalog.model.ProductOfferingPrice getProductOfferingPrice() {
        return ProductOfferingPrice;
    }

    public void setProductOfferingPrice(com.ericsson.modernization.services.productcatalog.model.ProductOfferingPrice productOfferingPrice) {
        ProductOfferingPrice = productOfferingPrice;
    }

    public com.ericsson.modernization.services.productcatalog.model.ProdCatProdOffer getProdCatProdOffer() {
        return ProdCatProdOffer;
    }

    public void setProdCatProdOffer(com.ericsson.modernization.services.productcatalog.model.ProdCatProdOffer prodCatProdOffer) {
        ProdCatProdOffer = prodCatProdOffer;
    }
}
