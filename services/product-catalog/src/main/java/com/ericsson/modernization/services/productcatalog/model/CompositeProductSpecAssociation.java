package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CompositeProductSpecAssociation extends EntityBase {

	@ManyToOne
	private ProductSpecification productSpecification;

	@ManyToOne
	private CompositeProductSpecification compositeProductSpecification;


    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public CompositeProductSpecification getCompositeProductSpecification() {
        return compositeProductSpecification;
    }

    public void setCompositeProductSpecification(CompositeProductSpecification compositeProductSpecification) {
        this.compositeProductSpecification = compositeProductSpecification;
    }
}
