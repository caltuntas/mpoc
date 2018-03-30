package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.request;

import com.ericsson.modernization.services.productcatalog.model.TimePeriod;

import javax.persistence.Embedded;
import java.util.Date;

public class ProductSpecCharacteristicEditRequest {
    private int ID;
    private String name;

    @Embedded
    private TimePeriod validFor;
    private int valueType;
    private String description;
    private String externalId;
    private String charValueString;
    private Boolean isConfigurable;
    private Boolean isRequired;

    public Boolean getisRequired() {
        return isRequired;
    }

    public void setisRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean getisConfigurable() {
        return isConfigurable;
    }

    public void setisConfigurable(Boolean configurable) {
        isConfigurable = configurable;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
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
