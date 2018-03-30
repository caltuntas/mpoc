package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering;

import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.CatalogAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingDetailModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response.ProductOfferingListModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.ProductSpecificationAppService;
import com.ericsson.modernization.services.productcatalog.model.*;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductOfferingAppService {

    @Autowired
    private ProductOfferingRepository productOfferingRepository;
    @Autowired
    private ProductSpecificationAppService productSpecificationAppService;
    @Autowired
    private CatalogAppService catalogAppService;

    public ProductOffering create(ProductOfferingDetailModel createRequest) {

        ProductOffering productOffering = new ProductOffering();
        return saveFields(productOffering, createRequest);

    }

    public ProductOffering update(ProductOfferingDetailModel editRequest) {
        ProductOffering productOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(editRequest.getId());
        if (productOffering != null) {

            return saveFields(productOffering, editRequest);
        }

        return null;
    }

    private ProductOffering saveFields(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {

        productOffering.setName(detailModel.getName());
        productOffering.setIsSellable(detailModel.getIsSellable());
        productOffering.setDescription(detailModel.getDescription());
        productOffering.setExternalId(productOffering.getExternalId());
        productOffering.setIsReplicated(detailModel.getIsReplicated());

        Duration returnPeriod = new Duration();
        returnPeriod.setPeriodValue(detailModel.getReturnPeriodValue());
        returnPeriod.setPeriodUnit(detailModel.getReturnPeriodUnit());
        productOffering.setReturnPeriod(returnPeriod);

        Duration warrantyPeriod = new Duration();
        warrantyPeriod.setPeriodValue(detailModel.getWarrantyPeriodValue());
        warrantyPeriod.setPeriodUnit(detailModel.getWarrantyPeriodUnit());
        productOffering.setWarrantyPeriod(warrantyPeriod);

        ProductSpecification specification = productSpecificationAppService.findById(detailModel.getProductSpecificationId());
        productOffering.setProductSpecification(specification);

        Catalog catalog = catalogAppService.findById(detailModel.getCatalogId());
        productOffering.setCatalog(catalog);


        productOffering.setSalesChannels(detailModel.getSalesChannels());
        productOffering.setSegments(detailModel.getSegments());
        productOffering.setDocuments(detailModel.getDocuments());

        return productOfferingRepository.save(productOffering);
    }

    public void delete(int productOfferingId) {
        ProductOffering productOffering = productOfferingRepository.findById(productOfferingId).get();
        if (productOffering != null) {
            productOffering.setDeleted(true);
            productOfferingRepository.save(productOffering);
        }
    }

    public ProductOffering findById(int id) {
        return productOfferingRepository.findByIdAndIsDeletedIsFalse(id);
    }

    public ProductOfferingDetailModel findByIdForEditing(int id) {
        ProductOffering productOffering = findById(id);
        return new ProductOfferingDetailModel(
                productOffering.getId(),
                productOffering.getName(),
                productOffering.getDescription(),
                productOffering.getIsReplicated(),
                productOffering.getIsSellable(),
                productOffering.getProductSpecification().getId(),
                productOffering.getCatalog().getId());
    }

    public List<ProductOfferingListModel> findAll() {
        return productOfferingRepository.findAllByIsDeletedIsFalse().stream()
                .map(x -> new ProductOfferingListModel(
                        x.getId(),
                        x.getName(),
                        x.getDescription(),
                        x.getProductSpecification() != null ? x.getProductSpecification().getCode() : null,
                        x.getCatalog() != null ? x.getCatalog().getName() : null,
                        x.getIsReplicated(),
                        x.getIsSellable(),
                        x.getValidFor() != null ? x.getValidFor().getValidForStartDate() : null,
                        x.getValidFor() != null ? x.getValidFor().getValidForEndDate() : null,
                        x.getWarrantyPeriod() != null ? x.getWarrantyPeriod().getPeriodValue() : 0,
                        x.getWarrantyPeriod() != null ? x.getWarrantyPeriod().getPeriodUnit() : 0,
                        x.getReturnPeriod() != null ? x.getReturnPeriod().getPeriodValue() : 0,
                        x.getReturnPeriod() != null ? x.getReturnPeriod().getPeriodUnit() : 0)
                ).collect(Collectors.toList());
    }
}
