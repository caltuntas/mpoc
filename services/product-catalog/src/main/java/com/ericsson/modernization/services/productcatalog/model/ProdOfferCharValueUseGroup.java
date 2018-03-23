package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProdOfferCharValueUseGroup extends EntityBase {

    @ManyToOne
    private ProductOffering productOffering;
    @ManyToOne
    private ProductSpecCharValueUseGroup productSpecCharValueUseGroup;

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }

    public ProductSpecCharValueUseGroup getProductSpecCharValueUseGroup() {
        return productSpecCharValueUseGroup;
    }

    public void setProductSpecCharValueUseGroup(ProductSpecCharValueUseGroup productSpecCharValueUseGroup) {
        this.productSpecCharValueUseGroup = productSpecCharValueUseGroup;
    }
}
