package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response;

public class IdNameDescriptionModel {
    public int id;
    public String name;
    public String description;

    public IdNameDescriptionModel(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }
}
