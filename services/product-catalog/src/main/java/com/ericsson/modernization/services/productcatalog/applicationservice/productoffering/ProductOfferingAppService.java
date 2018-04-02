package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingCharValueModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.ProdSpecCharValueUseAppService;
import com.ericsson.modernization.services.productcatalog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.CatalogAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.CategoryService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingDetailModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response.ProductOfferingListModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.ProductSpecificationAppService;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingRepository;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingTypeRepository;

@Transactional
@Service
public class ProductOfferingAppService {

    @Autowired
    private ProductOfferingRepository productOfferingRepository;
    @Autowired
    private ProductSpecificationAppService productSpecificationAppService;
    @Autowired
    private CatalogAppService catalogAppService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProdSpecCharValueUseAppService prodSpecCharValueUseAppService;
    @Autowired
    private ProductOfferingTypeRepository productOfferingTypeRepository;

    public ProductOffering create(ProductOfferingDetailModel createRequest) {

        ProductOffering productOffering = new ProductOffering();
        saveFields(productOffering, createRequest);
        if (createRequest.getProductOfferingTypeId() == 2)// Bundle
        {
            List<ProductOffering> relatedProductOfferings = new ArrayList<ProductOffering>();
            for (Integer id : createRequest.getProductOfferingIds()) {
                // BoChild kaydet
                ProductOffering childproductOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(id);
                // saveFields(childproductOffering, item);
                // Relation ata
                relatedProductOfferings.add(childproductOffering);
                // TODO : BoChild'ın ilişkilerini kaydet
            }
            // Relation kaydet
            productOffering.setRelatedProductOfferings(relatedProductOfferings);
            productOfferingRepository.save(productOffering);
        }

        return productOffering;
    }

    public ProductOffering update(ProductOfferingDetailModel editRequest) {
        ProductOffering productOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(editRequest.getId());
        if (productOffering != null) {
            saveFields(productOffering, editRequest);
        }
        return productOffering;
    }

    private void saveFields(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {

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

        saveSpesification(productOffering, detailModel);
        saveCatalog(productOffering, detailModel);
        saveCategory(productOffering, detailModel);

        productOffering.setSalesChannels(detailModel.getSalesChannels());
        productOffering.setSegments(detailModel.getSegments());
        productOffering.setDocuments(detailModel.getDocuments());

        Integer productOfferingTypeId = 1;
        if (detailModel.getProductOfferingTypeId() > 0)
            productOfferingTypeId = detailModel.getProductOfferingTypeId();
        ProductOfferingType productOfferingType = productOfferingTypeRepository.findById(productOfferingTypeId).get();
        productOffering.setProductOfferingType(productOfferingType);
        productOfferingRepository.save(productOffering);

        saveDetermines(productOffering, detailModel);
    }

    private void saveSpesification(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        ProductSpecification specification = productSpecificationAppService
                .findById(detailModel.getProductSpecificationId());
        productOffering.setProductSpecification(specification);
    }

    private void saveCatalog(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        Catalog catalog = catalogAppService.findById(detailModel.getCatalogId());
        productOffering.setCatalog(catalog);
    }

    private void saveCategory(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        Category category = categoryService.findById(detailModel.getCategoryId());
        productOffering.setCategory(category);
    }

    private void saveDetermines(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        List<ProductOfferingDetermines> productOfferingDetermines = new ArrayList<>();

        for (ProductOfferingCharValueModel model : detailModel.getProductOfferingCharValues()) {
            ProductOfferingDetermines determines = new ProductOfferingDetermines();
            ProdSpecCharValueUse prodSpecCharValueUse = prodSpecCharValueUseAppService.findById(model.getCharValueUseId());
            determines.setProductOffering(productOffering);
            determines.setProdSpecCharValueUse(prodSpecCharValueUse);
            productOfferingDetermines.add(determines);
        }

        if (productOfferingDetermines.size() > 0) {
            productOffering.setProductOfferingDetermineses(productOfferingDetermines);
            productOfferingRepository.save(productOffering);
        }
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
                productOffering.getCatalog().getId(),
                productOffering.getCategory().getId(),
                productOffering.getProductOfferingType().getId(),
                // productOffering.getRelatedProductOfferings());
                new ArrayList<Integer>(),
                new ArrayList<>() //TODO: will be edited after creating determines
        );
    }

    public List<ProductOfferingListModel> findAll() {
        return productOfferingRepository.findAllByIsDeletedIsFalse().stream()
                .map(x -> new ProductOfferingListModel(x.getId(), x.getName(), x.getDescription(),
                        x.getProductSpecification() != null ? x.getProductSpecification().getCode() : null,
                        x.getCatalog() != null ? x.getCatalog().getName() : null,
                        x.getCategory() != null ? x.getCategory().getCode() : null, x.getIsReplicated(),
                        x.getIsSellable(), x.getValidFor() != null ? x.getValidFor().getValidForStartDate() : null,
                        x.getValidFor() != null ? x.getValidFor().getValidForEndDate() : null,
                        x.getWarrantyPeriod() != null ? x.getWarrantyPeriod().getPeriodValue() : 0,
                        x.getWarrantyPeriod() != null ? x.getWarrantyPeriod().getPeriodUnit() : 0,
                        x.getReturnPeriod() != null ? x.getReturnPeriod().getPeriodValue() : 0,
                        x.getReturnPeriod() != null ? x.getReturnPeriod().getPeriodUnit() : 0,
                        x.getProductOfferingType() != null ? x.getProductOfferingType().getName() : null))
                .collect(Collectors.toList());
    }

    public List<ProductOfferingListModel> findAllByProductOfferingTypeId(int productOfferingTypeId) {
        return productOfferingRepository.findAllByProductOfferingTypeId(productOfferingTypeId).stream()
                .map(x -> new ProductOfferingListModel(x.getId(), x.getName(), x.getDescription(),
                        x.getProductSpecification() != null ? x.getProductSpecification().getCode() : null,
                        x.getCatalog() != null ? x.getCatalog().getName() : null,
                        x.getCategory() != null ? x.getCategory().getCode() : null, x.getIsReplicated(),
                        x.getIsSellable(), x.getValidFor() != null ? x.getValidFor().getValidForStartDate() : null,
                        x.getValidFor() != null ? x.getValidFor().getValidForEndDate() : null,
                        x.getWarrantyPeriod() != null ? x.getWarrantyPeriod().getPeriodValue() : 0,
                        x.getWarrantyPeriod() != null ? x.getWarrantyPeriod().getPeriodUnit() : 0,
                        x.getReturnPeriod() != null ? x.getReturnPeriod().getPeriodValue() : 0,
                        x.getReturnPeriod() != null ? x.getReturnPeriod().getPeriodUnit() : 0,
                        x.getProductOfferingType() != null ? x.getProductOfferingType().getName() : null))
                .collect(Collectors.toList());
    }
}
