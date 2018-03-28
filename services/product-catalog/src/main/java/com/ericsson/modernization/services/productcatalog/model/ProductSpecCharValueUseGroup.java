package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ProductSpecCharValueUseGroup extends EntityBase {
    @OneToMany(mappedBy = "productSpecCharValueUseGroup",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProdSpecCharValueUse> ProdSpecCharValueUses;

    public List<ProdSpecCharValueUse> getProdSpecCharValueUses() {
        return ProdSpecCharValueUses;
    }

    public void setProdSpecCharValueUses(List<ProdSpecCharValueUse> prodSpecCharValueUses) {
        ProdSpecCharValueUses = prodSpecCharValueUses;
    }
}
