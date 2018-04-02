package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductOfferingDetermines extends EntityBase {
    @ManyToOne
    private ProdSpecCharValueUse prodSpecCharValueUse;
    @ManyToOne
    private ProductOffering productOffering;


    public ProdSpecCharValueUse getProdSpecCharValueUse() {
        return prodSpecCharValueUse;
    }

    public void setProdSpecCharValueUse(ProdSpecCharValueUse prodSpecCharValueUse) {
        this.prodSpecCharValueUse = prodSpecCharValueUse;
    }

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }
}
