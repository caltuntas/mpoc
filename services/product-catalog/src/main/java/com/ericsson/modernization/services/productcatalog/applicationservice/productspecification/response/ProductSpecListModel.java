package com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductSpecListModel {
    public int id;
    public String name;
    public String code;
    public String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date createTime;

    public ProductSpecListModel(int id,String name, String code, String description, Date createTime){
        this.id=id;
        this.name=name;
        this.code=code;
        this.createTime=createTime;
        this.description=description;
    }
}
