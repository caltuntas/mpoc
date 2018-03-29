package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.request;

import java.util.Date;

public class ProductSpecCharacteristicCreateRequest {
    private String name;
    private Date validForStartDate;
    private Date validForEndDate;
    private int valueType;
    private String description;
    private String externalId;
    private String charValueString;
    private Boolean isConfigurable;
    private Boolean isRequired;

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean getConfigurable() {
        return isConfigurable;
    }

    public void setConfigurable(Boolean configurable) {
        isConfigurable = configurable;
    }

    public String getCharValueString() {
        return charValueString;
    }

    public void setCharValueString(String charValueString) {
        this.charValueString = charValueString;
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
}
