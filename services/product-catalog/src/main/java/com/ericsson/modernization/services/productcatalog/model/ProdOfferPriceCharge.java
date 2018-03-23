package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class ProdOfferPriceCharge extends ComponentProductOfferPrice {
    public ProdOfferPriceCharge() {
        alterations = new ArrayList<ProdOfferPriceAlteration>();
        alternates = new ArrayList<AlternateProdOfferPriceCharge>();
    }

    private Boolean isAltered;
    @Transient //TODO: karşılıklı List<A> List<B> ilişkisi sorgulanacak
    private List<ProdOfferPriceAlteration> alterations;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "prodOfferPriceCharge")
    private List<AlternateProdOfferPriceCharge> alternates;

    public Boolean getIsAltered() {
        return isAltered;
    }

    public void setIsAltered(Boolean isAltered) {
        this.isAltered = isAltered;
    }

    public List<ProdOfferPriceAlteration> getAlterations() {
        return alterations;
    }

    public void setAlterations(List<ProdOfferPriceAlteration> alterations) {
        this.alterations = alterations;
    }

    public List<AlternateProdOfferPriceCharge> getAlternates() {
        return alternates;
    }

    public void setAlternates(List<AlternateProdOfferPriceCharge> alternates) {
        this.alternates = alternates;
    }
}
