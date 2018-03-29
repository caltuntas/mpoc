package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.response;

import java.util.ArrayList;
import java.util.List;

public class ProdSpecCharValueUseListModel {
    private int prodSpecCharUseId;
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
}