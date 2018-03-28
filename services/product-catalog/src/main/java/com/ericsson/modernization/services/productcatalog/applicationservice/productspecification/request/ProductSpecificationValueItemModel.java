package com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.request;

import java.util.List;

public class ProductSpecificationValueItemModel {
    public ProductSpecificationValueItemModel(){}
    public ProductSpecificationValueItemModel(int id,List<Integer> selectedValueIds){
        this.id=id;
        this.selectedValueIds=selectedValueIds;

    }
    public int id;
    public List<Integer> selectedValueIds;
}

