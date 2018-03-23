package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductOfferingValuedBy extends EntityBase {

    @ManyToOne
    private ProductOffering ProductOffering;

    @ManyToOne
    private ProductOfferingPrice ProductOfferingPrice;

    public com.ericsson.modernization.services.productcatalog.model.ProductOffering getProductOffering() {
        return ProductOffering;
    }

    public void setProductOffering(com.ericsson.modernization.services.productcatalog.model.ProductOffering productOffering) {
        ProductOffering = productOffering;
    }

    public com.ericsson.modernization.services.productcatalog.model.ProductOfferingPrice getProductOfferingPrice() {
        return ProductOfferingPrice;
    }

    public void setProductOfferingPrice(com.ericsson.modernization.services.productcatalog.model.ProductOfferingPrice productOfferingPrice) {
        ProductOfferingPrice = productOfferingPrice;
    }
}
