package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductOfferingDetermines extends EntityBase {
    @ManyToOne
    private ProdSpecCharValueUse prodSpecCharValueUse;
    @ManyToOne
    private ProductOffering productOffering;
    @ManyToOne
    private ProductSpecCharacteristic productSpecCharacteristic;
    private String textValue;

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

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public ProductSpecCharacteristic getProductSpecCharacteristic() {
        return productSpecCharacteristic;
    }

    public void setProductSpecCharacteristic(ProductSpecCharacteristic productSpecCharacteristic) {
        this.productSpecCharacteristic = productSpecCharacteristic;
    }
}
