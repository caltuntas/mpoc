package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductSpecCharacteristic extends EntityBase
        implements Description, ExternalId, ValidFor, IsReplicated, Versioned {

    public ProductSpecCharacteristic() {
        productSpecCharacteristicValues = new ArrayList<ProductSpecCharacteristicValue>();
    }

    private String name;
    private int valueType;
    private String description;
    private String externalId;
    @Embedded
    private TimePeriod validFor;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "productSpecCharacteristic")
    private List<ProductSpecCharacteristicValue> productSpecCharacteristicValues;

    private Boolean isReplicated;

    private long versionNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
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

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public List<ProductSpecCharacteristicValue> getProductSpecCharacteristicValues() {
        return productSpecCharacteristicValues;
    }

    public void setProductSpecCharacteristicValues(
            List<ProductSpecCharacteristicValue> productSpecCharacteristicValues) {
        this.productSpecCharacteristicValues = productSpecCharacteristicValues;
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
