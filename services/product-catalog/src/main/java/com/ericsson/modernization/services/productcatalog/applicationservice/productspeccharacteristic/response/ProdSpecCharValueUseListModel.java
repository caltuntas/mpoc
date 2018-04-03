package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.response;

import java.util.ArrayList;
import java.util.List;

public class ProdSpecCharValueUseListModel {
    private int prodSpecCharUseId;
    private int prodSpecCharId;
    private int prodSpecCharType;
    private String prodSpecCharDescription;
    private Boolean isRequired;
    private Boolean isConfigurable;
    private List<ProdSpecCharValueListModel> prodSpecCharValueList;

    public ProdSpecCharValueUseListModel() {
        setProdSpecCharValueList(new ArrayList<>());
    }

    public List<ProdSpecCharValueListModel> getProdSpecCharValueList() {
        return prodSpecCharValueList;
    }

    public void setProdSpecCharValueList(List<ProdSpecCharValueListModel> prodSpecCharValueList) {
        this.prodSpecCharValueList = prodSpecCharValueList;
    }

    public int getProdSpecCharUseId() {
        return prodSpecCharUseId;
    }

    public void setProdSpecCharUseId(int prodSpecCharUseId) {
        this.prodSpecCharUseId = prodSpecCharUseId;
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

    public int getProdSpecCharType() {
        return prodSpecCharType;
    }

    public void setProdSpecCharType(int prodSpecCharType) {
        this.prodSpecCharType = prodSpecCharType;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean isConfigurable() {
        return isConfigurable;
    }

    public void setConfigurable(Boolean configurable) {
        isConfigurable = configurable;
    }
}
