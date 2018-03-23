package com.ericsson.modernization.services.productcatalog.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class CompositeProdOfferPrice extends ProductOfferingPrice {

    public CompositeProdOfferPrice() {
        children = new ArrayList<ProductOfferingPrice>();
    }

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "compositeProdOfferPrice")
    private List<ProductOfferingPrice> children;

    public List<ProductOfferingPrice> getChildren() {
        return children;
    }

    public void setChildren(List<ProductOfferingPrice> children) {
        this.children = children;
    }
}
