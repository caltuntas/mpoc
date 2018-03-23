package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;

@Entity
public class GenericProductSpecificationRelationship extends ProductSpecificationRelationship {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
