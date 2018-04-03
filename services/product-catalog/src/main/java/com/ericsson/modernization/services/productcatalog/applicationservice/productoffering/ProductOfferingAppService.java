package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ericsson.modernization.services.productcatalog.applicationservice.document.DocumentAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingCharValueModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response.IdNameDescriptionModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.ProdSpecCharValueUseAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.ProductSpecCharacteristicAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.saleschannel.SalesChannelAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.segment.SegmentAppService;
import com.ericsson.modernization.services.productcatalog.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.CatalogAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.CategoryService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingDetailModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response.ProductOfferingListModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.ProductSpecificationAppService;
import com.ericsson.modernization.services.productcatalog.model.Catalog;
import com.ericsson.modernization.services.productcatalog.model.Category;
import com.ericsson.modernization.services.productcatalog.model.Document;
import com.ericsson.modernization.services.productcatalog.model.Duration;
import com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import com.ericsson.modernization.services.productcatalog.model.ProductOfferingDetermines;
import com.ericsson.modernization.services.productcatalog.model.ProductOfferingType;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import com.ericsson.modernization.services.productcatalog.model.SalesChannel;
import com.ericsson.modernization.services.productcatalog.model.Segment;
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
    private ProductSpecCharacteristicAppService productSpecCharacteristicAppService;
    @Autowired
    private SalesChannelAppService salesChannelAppService;
    @Autowired
    private SegmentAppService segmentAppService;
    @Autowired
    private DocumentAppService documentAppService;
    @Autowired
    private ProductOfferingTypeRepository productOfferingTypeRepository;

    public ProductOffering create(ProductOfferingDetailModel createRequest) {

        ProductOffering productOffering = new ProductOffering();
        saveFields(productOffering, createRequest);// Simple or Bundle
        System.out.println(createRequest.getProductOfferingTypeId());
        if (createRequest.getProductOfferingTypeId() == 2)// Bundle
        {
            if (productOffering.getProductOfferings() == null)
                productOffering.setProductOfferings(new ArrayList<ProductOffering>());
            for (Integer id : createRequest.getSimpleProductOfferingIds()) {
                System.out.println("id : " + id);
                // BoChild kaydet
                ProductOffering childproductOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(id);
                ProductOffering clonedProductOffering = cloneChildProductOfferingForBundle(productOffering, childproductOffering);
                // Relation ata
                productOffering.getProductOfferings().add(clonedProductOffering);
            }
            // Relation kaydet
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

        saveReturnPeriod(productOffering, detailModel);
        saveWarrantyPeriod(productOffering, detailModel);
        saveSpesification(productOffering, detailModel);
        saveCatalog(productOffering, detailModel);
        saveCategory(productOffering, detailModel);

        //productOffering.setSalesChannels(detailModel.getSalesChannels());
        //productOffering.setSegments(detailModel.getSegments());
        //productOffering.setDocuments(detailModel.getDocuments());

        Integer productOfferingTypeId = 1;
        if (detailModel.getProductOfferingTypeId() > 0)
            productOfferingTypeId = detailModel.getProductOfferingTypeId();
        ProductOfferingType productOfferingType = productOfferingTypeRepository.findById(productOfferingTypeId).get();
        productOffering.setProductOfferingType(productOfferingType);
        productOfferingRepository.save(productOffering);

        saveDetermines(productOffering, detailModel);
        saveSalesChannels(productOffering, detailModel);
        saveSegments(productOffering, detailModel);
        saveDocuments(productOffering, detailModel);
        productOfferingRepository.save(productOffering);
    }

    private void saveSalesChannels(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        if (detailModel.getSalesChannels() != null) {
            for (int salesChannelId : detailModel.getSalesChannels()) {
                SalesChannel salesChannel = salesChannelAppService.findById(salesChannelId);
                if (salesChannel != null && !productOffering.getSalesChannels().contains(salesChannel)) {
                    productOffering.getSalesChannels().add(salesChannel);
                }
            }
        }
    }

    private void saveSegments(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        if (detailModel.getSegments() != null) {
            for (int segmentId : detailModel.getSegments()) {
                Segment segment = segmentAppService.findById(segmentId);
                if (segment != null && !productOffering.getSegments().contains(segment)) {
                    productOffering.getSegments().add(segment);
                }
            }
        }
    }

    private void saveDocuments(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        if (detailModel.getDocuments() != null) {
            for (int documentId : detailModel.getDocuments()) {
                Document document = documentAppService.findById(documentId);
                if (document != null && productOffering.getDocuments().contains(document)) {
                    productOffering.getDocuments().add(document);
                }
            }
        }
    }

    private void saveWarrantyPeriod(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        Duration warrantyPeriod = new Duration();
        warrantyPeriod.setPeriodValue(detailModel.getWarrantyPeriodValue());
        warrantyPeriod.setPeriodUnit(detailModel.getWarrantyPeriodUnit());
        productOffering.setWarrantyPeriod(warrantyPeriod);
    }

    private void saveReturnPeriod(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        Duration returnPeriod = new Duration();
        returnPeriod.setPeriodValue(detailModel.getReturnPeriodValue());
        returnPeriod.setPeriodUnit(detailModel.getReturnPeriodUnit());
        productOffering.setReturnPeriod(returnPeriod);
    }

    private void saveSpesification(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
        ProductSpecification specification = productSpecificationAppService
                .findById(detailModel.getProductSpecificationId());
        productOffering.setProductSpecification(specification);
    }

    private ProductOffering cloneChildProductOfferingForBundle(ProductOffering mainProductOffering,
                                                               ProductOffering childproductOffering) {
        ProductOffering clonedProductOffering = new ProductOffering();
        // TODO : Clone ManyToMany
        clonedProductOffering.setProductOfferingType(childproductOffering.getProductOfferingType());
        clonedProductOffering.setCatalog(childproductOffering.getCatalog());
        clonedProductOffering.setCategory(childproductOffering.getCategory());
        clonedProductOffering.setCreateUserId(childproductOffering.getCreateUserId());
        clonedProductOffering.setDescription(childproductOffering.getDescription());
        // clonedProductOffering.setDocuments(childproductOffering.getDocuments());
        clonedProductOffering.setExternalId(childproductOffering.getExternalId());
        clonedProductOffering.setIsReplicated(childproductOffering.getIsReplicated());
        clonedProductOffering.setName(childproductOffering.getName());
        // clonedProductOffering.setPrices(childproductOffering.getPrices());
        // clonedProductOffering.setProductOfferingDetermineses(childproductOffering.getProductOfferingDetermineses());
        ProductOfferingType boChildProductOfferingType = productOfferingTypeRepository.findByIdAndIsDeletedIsFalse(3);// BoChild
        clonedProductOffering.setProductOfferingType(boChildProductOfferingType);
        clonedProductOffering.setProductSpecification(childproductOffering.getProductSpecification());
        clonedProductOffering.setReturnPeriod(childproductOffering.getReturnPeriod());
        // clonedProductOffering.setSalesChannels(childproductOffering.getSalesChannels());
        // clonedProductOffering.setSegments(childproductOffering.getSegments());
        // clonedProductOffering.setUnsupportedProductSpecCharValueUseGroups(childproductOffering.getUnsupportedProductSpecCharValueUseGroups());
        clonedProductOffering.setUpdateUserId(childproductOffering.getUpdateUserId());
        clonedProductOffering.setValidFor(childproductOffering.getValidFor());
        clonedProductOffering.setWarrantyPeriod(childproductOffering.getWarrantyPeriod());
        productOfferingRepository.save(clonedProductOffering);
        return clonedProductOffering;
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
        //TODO: refactoring
        if (detailModel.getProductOfferingCharValues() != null) {
            List<ProductOfferingDetermines> productOfferingDetermines = new ArrayList<>();

            for (ProductOfferingCharValueModel model : detailModel.getProductOfferingCharValues()) {
                ProductOfferingDetermines determines = new ProductOfferingDetermines();
                boolean determinesExists = false;

                if (model.getCharValueType() == 1) {
                    ProdSpecCharValueUse prodSpecCharValueUse = prodSpecCharValueUseAppService.findById(model.getCharValueUseId());
                    determines.setProdSpecCharValueUse(prodSpecCharValueUse);

                    for (ProductOfferingDetermines persistedDetermines : productOffering.getProductOfferingDetermineses()) {
                        if (persistedDetermines.getProdSpecCharValueUse() != null
                                && persistedDetermines.getProdSpecCharValueUse().getId() == prodSpecCharValueUse.getId()) {
                            determinesExists = true;
                            break;
                        }
                    }
                }

                ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicAppService.findById(model.getCharId());
                determines.setProductSpecCharacteristic(productSpecCharacteristic);
                determines.setTextValue(model.getCharValue());
                determines.setProductOffering(productOffering);
                productOfferingDetermines.add(determines);

                if(!determinesExists){
                    productOffering.getProductOfferingDetermineses().add(determines);
                }
            }
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
                new ArrayList<Integer>(),
                findOfferingCharValues(id),
                getSalesChannelsIds(productOffering),
                getSegmentIds(productOffering),
                getDocumentIds(productOffering)
        );
    }

    public List<IdNameDescriptionModel> getSimgpleOfferingsForSelect(){
        return productOfferingRepository.findAllByProductOfferingTypeId(1).stream().map(x->new IdNameDescriptionModel(x.getId(),x.getName(),x.getDescription())).collect(Collectors.toList());
    }

    public List<ProductOfferingListModel> findAllByProductOfferingTypeId(int productOfferingTypeId) {
        return productOfferingRepository.findAllByProductOfferingTypeId(productOfferingTypeId).stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
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

    private List<ProductOfferingCharValueModel> findOfferingCharValues(int offeringId) {

        List<ProductOfferingCharValueModel> valueModelList = new ArrayList<>();

        ProductOffering productOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(offeringId);
        if (productOffering != null) {

            List<ProductOfferingDetermines> determinesList = productOffering.getProductOfferingDetermineses();
            for (ProductOfferingDetermines determines : determinesList) {

                ProductOfferingCharValueModel model = new ProductOfferingCharValueModel();

                if (determines.getProdSpecCharValueUse() != null) {
                    model.setCharValueUseId(determines.getProdSpecCharValueUse().getId());
                    model.setCharValue(determines.getProdSpecCharValueUse().getProductSpecCharacteristicValue().getValue());
                } else {
                    model.setCharValue(determines.getTextValue());
                }

                model.setCharValueType(determines.getProductSpecCharacteristic().getValueType());
                model.setCharId(determines.getProductSpecCharacteristic().getId());

                valueModelList.add(model);
            }
        }

        return valueModelList;
    }

    private List<Integer> getSegmentIds(ProductOffering productOffering) {
        //TODO: foreign keys will be persisted for performance
        List<Integer> segmentIds = new ArrayList<>();
        for (Segment segment : productOffering.getSegments()) {
            segmentIds.add(segment.getId());
        }
        return segmentIds;
    }

    private List<Integer> getSalesChannelsIds(ProductOffering productOffering) {
        //TODO: foreign keys will be persisted for performance
        List<Integer> salesChannelsIds = new ArrayList<>();
        for (SalesChannel salesChannel : productOffering.getSalesChannels()) {
            salesChannelsIds.add(salesChannel.getId());
        }
        return salesChannelsIds;
    }

    private List<Integer> getDocumentIds(ProductOffering productOffering) {
        //TODO: foreign keys will be persisted for performance
        List<Integer> documentIds = new ArrayList<>();
        for (Document document : productOffering.getDocuments()) {
            documentIds.add(document.getId());
        }
        return documentIds;
    }
}
