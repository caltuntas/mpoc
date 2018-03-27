package com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Request;

import java.util.List;

public class ProductSpecificationCreateRequest {
    public String name;
    public String code;
    public String description;
   /* public String status;
    public String productType;*/
    public List<ProductSpecificationValueItemModel> selectedCharacteristics;

}
