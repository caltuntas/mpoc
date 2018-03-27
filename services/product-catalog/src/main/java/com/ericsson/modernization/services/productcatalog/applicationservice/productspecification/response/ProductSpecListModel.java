package com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response;

import java.util.Date;

public class ProductSpecListModel {
    public int id;
    public String name;
    public String code;
    public String description;
    public Date createTime;

    public ProductSpecListModel(int id,String name, String code, String description, Date createTime){
        this.id=id;
        this.name=name;
        this.code=code;
        this.createTime=createTime;
        this.description=description;
    }
}
