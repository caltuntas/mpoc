package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/// <summary>
/// Represents the association between catalog and product offering.
/// </summary>
@Entity
public class ProdCatProdOffer extends EntityBase {
    @ManyToOne
    private Catalog catalog;
    @ManyToOne
    private ProductOffering productOffering;
    private String externalId;
    private Boolean isReplicated;
    private long versionNumber;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getIsReplicated() {
        return isReplicated;
    }

    public void setIsReplicated(Boolean isReplicated) {
        this.isReplicated = isReplicated;
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(long versionNumber) {
        this.versionNumber = versionNumber;
    }
}
