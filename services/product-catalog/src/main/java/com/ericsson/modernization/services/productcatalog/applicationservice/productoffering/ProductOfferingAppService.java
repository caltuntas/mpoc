package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.ericsson.modernization.services.productcatalog.model.Duration;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import com.ericsson.modernization.services.productcatalog.model.ProductOfferingType;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
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
	private ProductOfferingTypeRepository productOfferingTypeRepository;

	public ProductOffering create(ProductOfferingDetailModel createRequest) {

		ProductOffering productOffering = new ProductOffering();
		saveFields(productOffering, createRequest);// Simple or Bundle
		System.out.println(createRequest.getProductOfferingTypeId());
		if (createRequest.getProductOfferingTypeId() == 2)// Bundle
		{
			Set<ProductOffering> relatedProductOfferings = new HashSet<ProductOffering>();
			for (Integer id : createRequest.getSimpleProductOfferingIds()) {
				System.out.println("id : " + id);
				// BoChild kaydet
				ProductOffering childproductOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(id);
				cloneChildProductOfferingForBundle(productOffering, childproductOffering);
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

		ProductSpecification specification = productSpecificationAppService
				.findById(detailModel.getProductSpecificationId());
		productOffering.setProductSpecification(specification);

		Catalog catalog = catalogAppService.findById(detailModel.getCatalogId());
		productOffering.setCatalog(catalog);

		Category category = categoryService.findById(detailModel.getCategoryId());
		productOffering.setCategory(category);

		productOffering.setSalesChannels(detailModel.getSalesChannels());
		productOffering.setSegments(detailModel.getSegments());
		productOffering.setDocuments(detailModel.getDocuments());

		Integer productOfferingTypeId = 1;// Simple
		if (detailModel.getProductOfferingTypeId() > 0)
			productOfferingTypeId = detailModel.getProductOfferingTypeId();
		ProductOfferingType productOfferingType = productOfferingTypeRepository.findById(productOfferingTypeId).get();
		productOffering.setProductOfferingType(productOfferingType);
		productOfferingRepository.save(productOffering);
	}

	private void cloneChildProductOfferingForBundle(ProductOffering mainProductOffering, ProductOffering childproductOffering) {
		ProductOffering clonedProductOffering = new ProductOffering();
		clonedProductOffering.setName(childproductOffering.getName());
		clonedProductOffering.setIsSellable(childproductOffering.getIsSellable());
		clonedProductOffering.setDescription(childproductOffering.getDescription());
		clonedProductOffering.setExternalId(childproductOffering.getExternalId());
		clonedProductOffering.setIsReplicated(childproductOffering.getIsReplicated());
		clonedProductOffering.setReturnPeriod(childproductOffering.getReturnPeriod());
		clonedProductOffering.setWarrantyPeriod(childproductOffering.getWarrantyPeriod());
		clonedProductOffering.setProductSpecification(childproductOffering.getProductSpecification());
		clonedProductOffering.setCategory(childproductOffering.getCategory());
		//clonedProductOffering.setSalesChannels(childproductOffering.getSalesChannels());
		//clonedProductOffering.setSegments(childproductOffering.getSegments());
		//clonedProductOffering.setDocuments(childproductOffering.getDocuments());
		clonedProductOffering.setProductOfferingType(childproductOffering.getProductOfferingType());
		Set<ProductOffering> mainProductOfferings = new HashSet<ProductOffering>();
		mainProductOfferings.add(mainProductOffering);
		clonedProductOffering.setMainProductOfferings(mainProductOfferings);
		productOfferingRepository.save(clonedProductOffering);
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
		return new ProductOfferingDetailModel(productOffering.getId(), productOffering.getName(),
				productOffering.getDescription(), productOffering.getIsReplicated(), productOffering.getIsSellable(),
				productOffering.getProductSpecification().getId(), productOffering.getCatalog().getId(),
				productOffering.getCategory().getId(), productOffering.getProductOfferingType().getId(),
				// productOffering.getRelatedProductOfferings());
				new ArrayList<Integer>());
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
