package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/// <summary>
/// Represents the specific characteristics that is used for a specific product specification
/// </summary>
@Entity
public class ProductSpecCharUse extends EntityBase
        implements Description, ValidFor, ExternalId, IsReplicated, Versioned {


    public final int Type_None = 0;
    public final int Type_Stock = 1;

    public ProductSpecCharUse() {
        productSpecCharValueUses = new ArrayList<ProdSpecCharValueUse>();
    }

    private String name;
    private String description;

    /// <summary>
    /// Gets or sets the value type of the product specification characteristic
    /// 0 = All (Default)
    /// 1 = Stock
    /// </summary>
    private int characteristicType;

    @ManyToOne
    private ProductSpecCharacteristic productSpecCharacteristic;
    @ManyToOne
    private ProductSpecification productSpecification;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "productSpecCharUse")
    private List<ProdSpecCharValueUse> productSpecCharValueUses;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCharacteristicType() {
        return characteristicType;
    }

    public void setCharacteristicType(int characteristicType) {
        this.characteristicType = characteristicType;
    }

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
