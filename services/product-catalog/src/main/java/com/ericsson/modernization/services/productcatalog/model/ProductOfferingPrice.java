package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class ProductOfferingPrice extends EntityBase
        implements ExternalId, IsReplicated, Versioned, Description {

    private String name;
    private String description;
    private String externalId;
    private Boolean isReplicated;
    private long versionNumber;
    @ManyToOne
    private CompositeProdOfferPrice compositeProdOfferPrice;
    private int executionOrder;
    @ManyToOne
    private ProductOffering productOffering;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CompositeProdOfferPrice getCompositeProdOfferPrice() {
        return compositeProdOfferPrice;
    }

    public void setCompositeProdOfferPrice(CompositeProdOfferPrice compositeProdOfferPrice) {
        this.compositeProdOfferPrice = compositeProdOfferPrice;
    }

    public int getExecutionOrder() {
        return executionOrder;
    }

    public void setExecutionOrder(int executionOrder) {
        this.executionOrder = executionOrder;
    }

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }
}
