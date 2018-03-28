package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/// <summary>
/// Represents the specific characteristics that is used for a specific product specification
/// </summary>
@Entity
public class ProductSpecCharUse extends EntityBase
        implements  ValidFor, ExternalId, IsReplicated, Versioned {

public ProductSpecCharUse()
{
    this.productSpecCharValueUses=new ArrayList<ProdSpecCharValueUse>();
}

    @ManyToOne
    private ProductSpecCharacteristic productSpecCharacteristic;
    @ManyToOne
    private ProductSpecification productSpecification;
    @OneToMany(mappedBy = "productSpecCharUse",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProdSpecCharValueUse> productSpecCharValueUses;
    @Embedded
    private TimePeriod validFor;
    private String externalId;
    private Boolean isReplicated;
    private long versionNumber;
    public ProductSpecCharacteristic getProductSpecCharacteristic() {
        return productSpecCharacteristic;
    }

    public void setProductSpecCharacteristic(ProductSpecCharacteristic productSpecCharacteristic) {
        this.productSpecCharacteristic = productSpecCharacteristic;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public List<ProdSpecCharValueUse> getProductSpecCharValueUses() {
        return productSpecCharValueUses;
    }

    public void setProductSpecCharValueUses(List<ProdSpecCharValueUse> productSpecCharValueUses) {
        this.productSpecCharValueUses = productSpecCharValueUses;
    }

    public void addProductSpecCharValueUse(ProdSpecCharValueUse productSpecCharValueUse) {
        productSpecCharValueUse.setProductSpecCharUse(this);
      this.productSpecCharValueUses.add(productSpecCharValueUse);
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

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }
}
