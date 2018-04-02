package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request;

public class ProductOfferingCharValueModel {
    private int charId;
    private String  charValue;
    private int charValueUseId;
    private int charValueType ;

    public ProductOfferingCharValueModel(){

    }

    public ProductOfferingCharValueModel(
            int charId,
            int charValueType,
            int charValueUseId,
            String charValue){
        setCharId(charId);
        setCharValue(charValue);
        setCharValueType(charValueType);
        setCharValueUseId(charValueUseId);
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public String getCharValue() {
        return charValue;
    }

    public void setCharValue(String charValue) {
        this.charValue = charValue;
    }

    public int getCharValueUseId() {
        return charValueUseId;
    }

    public void setCharValueUseId(int charValueUseId) {
        this.charValueUseId = charValueUseId;
    }

    public int getCharValueType() {
        return charValueType;
    }

    public void setCharValueType(int charValueType) {
        this.charValueType = charValueType;
    }
}
