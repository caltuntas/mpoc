package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductOfferingDetermines extends EntityBase {
    @ManyToOne
    private ProdSpecCharValueUse ProdSpecCharValueUse;
    @ManyToOne
    private ProductOffering ProductOffering;

    public com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse getProdSpecCharValueUse() {
        return ProdSpecCharValueUse;
    }

    public void setProdSpecCharValueUse(com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse prodSpecCharValueUse) {
        ProdSpecCharValueUse = prodSpecCharValueUse;
    }

    public com.ericsson.modernization.services.productcatalog.model.ProductOffering getProductOffering() {
        return ProductOffering;
    }

    public void setProductOffering(com.ericsson.modernization.services.productcatalog.model.ProductOffering productOffering) {
        ProductOffering = productOffering;
    }
}
