
package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ProductSpecificationRelationship extends EntityBase {
    @ManyToOne
    private ProductSpecification productSpecification;
    @ManyToOne
    private ProductSpecification relatedProductSpecification;
    @ManyToOne
    private ProductSpecificationRelationshipType RelationshipType;

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public ProductSpecification getRelatedProductSpecification() {
        return relatedProductSpecification;
    }

    public void setRelatedProductSpecification(ProductSpecification relatedProductSpecification) {
        this.relatedProductSpecification = relatedProductSpecification;
    }

    public ProductSpecificationRelationshipType getRelationshipType() {
        return RelationshipType;
    }

    public void setRelationshipType(ProductSpecificationRelationshipType relationshipType) {
        RelationshipType = relationshipType;
    }
}
