package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/// <summary>
/// Represents the specific characteristics that is used for a specific product specification
/// </summary>
@Entity
public class ProductSpecCharUse extends EntityBase {

    public ProductSpecCharUse() {
        this.setProductSpecCharValueUses(new ArrayList<ProdSpecCharValueUse>());
    }

    @ManyToOne
    private ProductSpecCharacteristic productSpecCharacteristic;
    @ManyToOne
    private ProductSpecification productSpecification;
    @OneToMany(mappedBy = "productSpecCharUse",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProdSpecCharValueUse> productSpecCharValueUses;


    public void setProductSpecCharacteristic(ProductSpecCharacteristic productSpecCharacteristic) {
        this.productSpecCharacteristic = productSpecCharacteristic;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public List<ProdSpecCharValueUse> getProductSpecCharValueUses() {
        return productSpecCharValueUses;
    }

    public void setProductSpecCharValueUses(List<ProdSpecCharValueUse> productSpecCharValueUses) {
        this.productSpecCharValueUses = productSpecCharValueUses;
    }

    public void addProductSpecCharValueUse(ProdSpecCharValueUse productSpecCharValueUse) {
        productSpecCharValueUse.setProductSpecCharUse(this);
      this.productSpecCharValueUses.add(productSpecCharValueUse);
    }

}
