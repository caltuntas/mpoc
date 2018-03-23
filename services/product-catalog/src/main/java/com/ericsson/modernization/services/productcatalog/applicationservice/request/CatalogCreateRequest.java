package com.ericsson.modernization.services.productcatalog.applicationservice.request;

import java.util.Date;

public class CatalogCreateRequest {
    private String name;
    private Date validForStartDate;
    private Date validForEndDate;
    private String description;
    private String externalId;
    private int catalogSpecificationId;
    private Boolean isReplicated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidForStartDate() {
        return validForStartDate;
    }

    public void setValidForStartDate(Date validForStartDate) {
        this.validForStartDate = validForStartDate;
    }

    public Date getValidForEndDate() {
        return validForEndDate;
    }

    public void setValidForEndDate(Date validForEndDate) {
        this.validForEndDate = validForEndDate;
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

    public int getCatalogSpecificationId() {
        return catalogSpecificationId;
    }

    public void setCatalogSpecificationId(int productSpecificationId) {
        catalogSpecificationId = catalogSpecificationId;
    }

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }

}
