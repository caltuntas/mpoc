package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProdSpecCharValueUseInfluences extends EntityBase {

    @ManyToOne
    private ProdSpecCharValueUse prodSpecCharValueUse;

    @ManyToOne
    private ProductOfferingPrice productOfferingPrice;

    public ProdSpecCharValueUse getProdSpecCharValueUse() {
        return prodSpecCharValueUse;
    }

    public void setProdSpecCharValueUse(ProdSpecCharValueUse prodSpecCharValueUse) {
        this.prodSpecCharValueUse = prodSpecCharValueUse;
    }

    public ProductOfferingPrice getProductOfferingPrice() {
        return productOfferingPrice;
    }

    public void setProductOfferingPrice(ProductOfferingPrice productOfferingPrice) {
        this.productOfferingPrice = productOfferingPrice;
    }
}
