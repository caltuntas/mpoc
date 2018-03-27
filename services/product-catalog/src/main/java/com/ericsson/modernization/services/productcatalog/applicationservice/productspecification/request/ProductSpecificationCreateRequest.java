package com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.request;


import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.request.ProductSpecificationValueItemModel;

import java.util.List;

public class ProductSpecificationCreateRequest {
    public String name;
    public String code;
    public String description;
   /* public String status;
    public String productType;*/
    public List<ProductSpecificationValueItemModel> selectedCharacteristics;

}
