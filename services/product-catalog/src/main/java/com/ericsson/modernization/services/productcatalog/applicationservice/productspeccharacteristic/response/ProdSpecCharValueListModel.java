package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.response;

public class ProdSpecCharValueListModel {
    private int prodSpecCharValueUseId;
    private int prodSpecCharValueId;
    private String prodSpecCharValue;
    private int prodSpecCharId;
    private String prodSpecCharDescription;

    public int getProdSpecCharValueId() {
        return prodSpecCharValueId;
    }

    public void setProdSpecCharValueId(int prodSpecCharValueId) {
        this.prodSpecCharValueId = prodSpecCharValueId;
    }

    public String getProdSpecCharValue() {
        return prodSpecCharValue;
    }

    public void setProdSpecCharValue(String prodSpecCharValue) {
        this.prodSpecCharValue = prodSpecCharValue;
    }

    public int getProdSpecCharId() {
        return prodSpecCharId;
    }

    public void setProdSpecCharId(int prodSpecCharId) {
        this.prodSpecCharId = prodSpecCharId;
    }

    public String getProdSpecCharDescription() {
        return prodSpecCharDescription;
    }

    public void setProdSpecCharDescription(String prodSpecCharDescription) {
        this.prodSpecCharDescription = prodSpecCharDescription;
    }


    public int getProdSpecCharValueUseId() {
        return prodSpecCharValueUseId;
    }

    public void setProdSpecCharValueUseId(int prodSpecCharValueUseId) {
        this.prodSpecCharValueUseId = prodSpecCharValueUseId;
    }
}
