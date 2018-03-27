package com.ericsson.modernization.services.productcatalog.applicationservice.catalog.response;

import java.util.Date;

public class CatalogListResponse {
    private int id;
    private String name;
    private Date validForStartDate;
    private Date validForEndDate;
    private String description;
    private String externalId;
    private Boolean isReplicated;

    public CatalogListResponse() {
    }

    public CatalogListResponse(
            int id,
            String name,
            Date validForStartDate,
            Date validForEndDate,
            String description,
            String externalId,
            Boolean isReplicated) {

        setId(id);
        setName(name);
        setDescription(description);
        setExternalId(externalId);
        setReplicated(isReplicated);
        setValidForEndDate(validForEndDate);
        setValidForStartDate(validForStartDate);
    }

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

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


