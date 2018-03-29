package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.response;

public class ProdSpecCharValueListModel {
    private int prodSpecCharValueUseId;
    private int prodSpecCharValueId;
    private String prodSpecCharValue;

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

    public int getProdSpecCharValueUseId() {
        return prodSpecCharValueUseId;
    }

    public void setProdSpecCharValueUseId(int prodSpecCharValueUseId) {
        this.prodSpecCharValueUseId = prodSpecCharValueUseId;
    }
}
