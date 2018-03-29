package com.ericsson.modernization.services.productcatalog.applicationservice.catalog.request;

import com.ericsson.modernization.services.productcatalog.model.TimePeriod;

import javax.persistence.Embedded;
import java.util.Date;

public class CatalogEditRequest {
    private int id;
    private String name;
    @Embedded
    private TimePeriod validFor;
    private String description;
    private String externalId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
