package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ProductOfferingTerm extends EntityBase implements Description, ValidFor {

    private int term;
    private String desc;
    private String type;
    @OneToOne(cascade = CascadeType.ALL)
    private ProductOffering productOffering;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public TimePeriod getValidFor() {
        return null;
    }

    @Override
    public void setValidFor(TimePeriod validFor) {

    }

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }
}

