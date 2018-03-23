package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UnsupportedProductSpecCharValueUseGroup extends ProductSpecCharValueUseGroup {
    @ManyToOne
    private ProductOffering productOffering;
}
