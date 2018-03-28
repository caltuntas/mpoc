package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductOfferingDetermines extends EntityBase {
    @ManyToOne
    private ProdSpecCharValueUse prodSpecCharValueUse;
    @ManyToOne
    private ProductOffering productOffering;

    public com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse getProdSpecCharValueUse() {
        return prodSpecCharValueUse;
    }

    public void setProdSpecCharValueUse(com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse prodSpecCharValueUse) {
        prodSpecCharValueUse = prodSpecCharValueUse;
    }

    public com.ericsson.modernization.services.productcatalog.model.ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(com.ericsson.modernization.services.productcatalog.model.ProductOffering productOffering) {
        productOffering = productOffering;
    }
}
