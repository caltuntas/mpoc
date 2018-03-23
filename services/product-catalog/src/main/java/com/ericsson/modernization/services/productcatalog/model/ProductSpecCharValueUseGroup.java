package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ProductSpecCharValueUseGroup extends EntityBase {
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "productSpecCharValueUseGroup")
    private List<ProdSpecCharValueUse> ProdSpecCharValueUses;

    public List<ProdSpecCharValueUse> getProdSpecCharValueUses() {
        return ProdSpecCharValueUses;
    }

    public void setProdSpecCharValueUses(List<ProdSpecCharValueUse> prodSpecCharValueUses) {
        ProdSpecCharValueUses = prodSpecCharValueUses;
    }
}
