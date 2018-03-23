package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductSpecCharValueUseGroupRelationship extends EntityBase {
    @ManyToOne
    private ProdSpecCharValueUse ProdSpecCharValueUse;
    @ManyToOne
    private ProductSpecCharValueUseGroup ProductSpecCharValueUseGroup;

    public com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse getProdSpecCharValueUse() {
        return ProdSpecCharValueUse;
    }

    public void setProdSpecCharValueUse(com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse prodSpecCharValueUse) {
        ProdSpecCharValueUse = prodSpecCharValueUse;
    }

    public com.ericsson.modernization.services.productcatalog.model.ProductSpecCharValueUseGroup getProductSpecCharValueUseGroup() {
        return ProductSpecCharValueUseGroup;
    }

    public void setProductSpecCharValueUseGroup(com.ericsson.modernization.services.productcatalog.model.ProductSpecCharValueUseGroup productSpecCharValueUseGroup) {
        ProductSpecCharValueUseGroup = productSpecCharValueUseGroup;
    }
}
