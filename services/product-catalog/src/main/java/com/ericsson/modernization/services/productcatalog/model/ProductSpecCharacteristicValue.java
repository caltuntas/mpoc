
package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductSpecCharacteristicValue extends EntityBase
        implements ValidFor, ExternalId, IsReplicated, Versioned {
public ProductSpecCharacteristicValue()
{
    this.productSpecCharValueUses=new ArrayList<ProdSpecCharValueUse>();
}
    private String value;
    @ManyToOne
    private ProductSpecCharacteristic productSpecCharacteristic;
    @Embedded
    private TimePeriod validFor;
    private String externalId;
    private Boolean isReplicated;
    private long versionNumber;

    @OneToMany(mappedBy = "productSpecCharacteristicValue")
    private List<ProdSpecCharValueUse> productSpecCharValueUses;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProductSpecCharacteristic getProductSpecCharacteristic() {
        return productSpecCharacteristic;
    }

    public void setProductSpecCharacteristic(ProductSpecCharacteristic productSpecCharacteristic) {
        this.productSpecCharacteristic = productSpecCharacteristic;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getIsReplicated() {
        return getReplicated();
    }

    public void setIsReplicated(Boolean isReplicated) {
        this.setReplicated(isReplicated);
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(long versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }

    public List<ProdSpecCharValueUse> getProductSpecCharValueUses() {
        return productSpecCharValueUses;
    }

    public void setProductSpecCharValueUses(List<ProdSpecCharValueUse> productSpecCharValueUses) {
        this.productSpecCharValueUses = productSpecCharValueUses;
    }

    public void AddValueUse(ProdSpecCharValueUse valUse){
        this.productSpecCharValueUses.add(valUse);
    }
}
