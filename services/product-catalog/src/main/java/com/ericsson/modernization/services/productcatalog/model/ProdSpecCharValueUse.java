package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/// <summary>
/// Represents the specific characteristic value that is used for a specific characteristic of a product specification
/// </summary>
@Entity
public class ProdSpecCharValueUse extends EntityBase implements ValidFor, ExternalId, IsReplicated, Versioned {

    private String name;
    @ManyToOne
    private ProductOffering productOffering;
    @ManyToOne
    private ProductSpecCharacteristicValue productSpecCharacteristicValue;
    @ManyToOne
    private ProductSpecCharValueUseGroup productSpecCharValueUseGroup;
    @ManyToOne
    private ProductSpecCharUse productSpecCharUse;
    @Embedded
    private TimePeriod validFor;
    private String externalId;
    private Boolean isReplicated;

    private long versionNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductSpecCharacteristicValue getProductSpecCharacteristicValue() {
        return productSpecCharacteristicValue;
    }

    public void setProductSpecCharacteristicValue(ProductSpecCharacteristicValue productSpecCharacteristicValue) {
        this.productSpecCharacteristicValue = productSpecCharacteristicValue;
    }

    public ProductSpecCharUse getProductSpecCharUse() {
        return productSpecCharUse;
    }

    public void setProductSpecCharUse(ProductSpecCharUse productSpecCharUse) {
        this.productSpecCharUse = productSpecCharUse;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
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

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }

    public ProductSpecCharValueUseGroup getProductSpecCharValueUseGroup() {
        return productSpecCharValueUseGroup;
    }

    public void setProductSpecCharValueUseGroup(ProductSpecCharValueUseGroup productSpecCharValueUseGroup) {
        this.productSpecCharValueUseGroup = productSpecCharValueUseGroup;
    }

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }
}
