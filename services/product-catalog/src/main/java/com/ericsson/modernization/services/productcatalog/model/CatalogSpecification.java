package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class CatalogSpecification extends EntityBase implements Description, ValidFor {
    private String name;
    @Embedded
    private TimePeriod validFor;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
