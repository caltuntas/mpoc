package com.ericsson.modernization.services.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Catalog extends EntityBase implements Description, ValidFor, ExternalId, IsReplicated, Versioned {
    private String name;
    @Embedded
    private TimePeriod validFor;
    private String description;
    @ManyToOne
    private CatalogSpecification catalogSpecification;
    @JsonIgnore
    @OneToMany(mappedBy = "catalog",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProductOffering> productOfferings;

    private String externalId;
    private Boolean isReplicated;
    private long versionNumber;

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

    public CatalogSpecification getCatalogSpecification() {
        return catalogSpecification;
    }

    public void setCatalogSpecification(CatalogSpecification catalogSpecification) {
        this.catalogSpecification = catalogSpecification;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getIsReplicated() {
        return getReplicated();
    }

    public void setIsReplicated(Boolean isReplicated) {
        this.setReplicated(isReplicated);
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(long versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }
}
