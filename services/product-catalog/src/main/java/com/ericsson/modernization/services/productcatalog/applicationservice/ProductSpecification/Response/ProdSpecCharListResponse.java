package com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Response;

import java.util.List;

public class ProdSpecCharListResponse {
    public ProdSpecCharListResponse(int id, String name, int valueType,List<ProdSpecCharValueModel> values ){
        this.id=id;
        this.name=name;
        this.valueType=valueType;
        this.values=values;
    }
    public int id;
    public String name;
    public int valueType;
    public List<ProdSpecCharValueModel> values;


}
