package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductOfferingRole extends BusinessInteractionRole {
    @ManyToOne
    private ProductOffering ProductOffering;

    public com.ericsson.modernization.services.productcatalog.model.ProductOffering getProductOffering() {
        return ProductOffering;
    }

    public void setProductOffering(com.ericsson.modernization.services.productcatalog.model.ProductOffering productOffering) {
        ProductOffering = productOffering;
    }
}
