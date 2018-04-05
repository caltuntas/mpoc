package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.CatalogAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.CategoryService;
import com.ericsson.modernization.services.productcatalog.applicationservice.document.DocumentAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.price.PriceAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.price.request.PriceRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingCharValueModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingDetailModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response.IdNameDescriptionModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response.ProductOfferingListModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.ProdSpecCharValueUseAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.ProductSpecCharacteristicAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.ProductSpecificationAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.saleschannel.SalesChannelAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.segment.SegmentAppService;
import com.ericsson.modernization.services.productcatalog.model.Catalog;
import com.ericsson.modernization.services.productcatalog.model.Category;
import com.ericsson.modernization.services.productcatalog.model.Document;
import com.ericsson.modernization.services.productcatalog.model.Duration;
import com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import com.ericsson.modernization.services.productcatalog.model.ProductOfferingDetermines;
import com.ericsson.modernization.services.productcatalog.model.ProductOfferingTerm;
import com.ericsson.modernization.services.productcatalog.model.ProductOfferingType;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import com.ericsson.modernization.services.productcatalog.model.SalesChannel;
import com.ericsson.modernization.services.productcatalog.model.Segment;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingRepository;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingTypeRepository;
import com.ericsson.modernization.services.productcatalog.repository.util.QueryExecuter;

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
	private PriceAppService priceAppService;
	@Autowired
	private ProductOfferingTypeRepository productOfferingTypeRepository;

	public ProductOffering create(ProductOfferingDetailModel createRequest) {
		ProductOffering productOffering = new ProductOffering();
		saveFields(productOffering, createRequest);// Simple or Bundle
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

		ProductOfferingType productOfferingType = productOfferingTypeRepository
				.findById(detailModel.getProductOfferingTypeId()).get();
		productOffering.setProductOfferingType(productOfferingType);

		productOfferingRepository.save(productOffering);

		saveDetermines(productOffering, detailModel.getProductOfferingCharValues());
		saveSalesChannels(productOffering, detailModel.getSalesChannels());
		saveSegments(productOffering, detailModel.getSegments());
		saveDocuments(productOffering, detailModel.getDocuments());
		saveTerm(productOffering, detailModel.getTerm());

		saveFieldsForBundle(productOffering, detailModel);

		productOfferingRepository.save(productOffering);
		savePrices(productOffering, detailModel.getPriceRequestList());
	}

	private void saveFieldsForBundle(ProductOffering productOffering, ProductOfferingDetailModel detailModel) {
		if (detailModel.getProductOfferingTypeId() != 2)// Bundle
			return;
		Map<Integer, Integer> childOfferingIdAndClonnedOfferingMap = new HashMap<Integer, Integer>();
		List<Integer> oldList = new ArrayList<Integer>();
		if (productOffering.getId() > 0) {
			// oldList =
			// QueryExecuter.GetBundleRelatedSimpleOfferingIds(productOffering.getId());
			childOfferingIdAndClonnedOfferingMap = QueryExecuter
					.GetBundleRelatedSimpleAndChildOfferingIdsHash(productOffering.getId());
			oldList = new ArrayList<>(childOfferingIdAndClonnedOfferingMap.keySet());
		}
		List<Integer> newList = detailModel.getSimpleProductOfferingIds().stream().distinct()
				.collect(Collectors.toList());
		if (productOffering.getProductOfferings() == null)
			productOffering.setProductOfferings(new ArrayList<ProductOffering>());

		List<Integer> removed = new ArrayList<Integer>(oldList);
		removed.removeAll(newList);

		List<Integer> same = new ArrayList<Integer>(oldList);
		same.retainAll(newList);

		List<Integer> added = new ArrayList<Integer>(newList);
		added.removeAll(oldList);

		for (Integer id : added) {
			// BoChild kaydet
			ProductOffering simpleProductOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(id);
			if (simpleProductOffering != null) {
				// Klonla
				ProductOffering clonedProductOffering = cloneChildProductOfferingForBundle(productOffering,
						simpleProductOffering);
				// Relation ata
				productOffering.getProductOfferings().add(clonedProductOffering);
			}
		}
		for (Integer id : removed) {
			ProductOffering simpleProductOffering = productOfferingRepository.findByIdAndIsDeletedIsFalse(id);
			if (simpleProductOffering != null) {
				// Klonlanmış kaydı bul, sil, ilişkisini koparma.
				System.out.println("simpleProductOfferingId:" + simpleProductOffering.getId());
				Integer clonedProductOfferingId = childOfferingIdAndClonnedOfferingMap
						.get(simpleProductOffering.getId());
				System.out.println("clonedProductOfferingId:" + clonedProductOfferingId);
				ProductOffering clonedProductOffering = productOfferingRepository
						.findByIdAndIsDeletedIsFalse(clonedProductOfferingId);
				clonedProductOffering.setDeleted(true);
				productOfferingRepository.save(clonedProductOffering);
				// productOffering.getProductOfferings().remove(clonedProductOffering);
			}
		}
		// Relation kaydet
		// productOfferingRepository.save(productOffering);
	}

	private void savePrices(ProductOffering productOffering, List<PriceRequest> priceRequestList) {
		priceAppService.savePrices(priceRequestList, productOffering);
	}

	private void saveTerm(ProductOffering productOffering, int term) {

		if (productOffering.getProductOfferingTerm() != null) {
			productOffering.getProductOfferingTerm().setTerm(term);
		} else {
			ProductOfferingTerm productOfferingTerm = new ProductOfferingTerm();
			productOfferingTerm.setTerm(term);
			productOfferingTerm.setProductOffering(productOffering);
			productOffering.setProductOfferingTerm(productOfferingTerm);
		}
	}

	private void saveSalesChannels(ProductOffering productOffering, List<Integer> salesChannelIdList) {
		if (salesChannelIdList != null) {
			for (int salesChannelId : salesChannelIdList) {
				SalesChannel salesChannel = salesChannelAppService.findById(salesChannelId);
				if (salesChannel != null && !productOffering.getSalesChannels().contains(salesChannel)) {
					productOffering.getSalesChannels().add(salesChannel);
				}
			}
		}
	}

	private void saveSegments(ProductOffering productOffering, List<Integer> segmentIdList) {
		if (segmentIdList != null) {
			for (int segmentId : segmentIdList) {
				Segment segment = segmentAppService.findById(segmentId);
				if (segment != null && !productOffering.getSegments().contains(segment)) {
					productOffering.getSegments().add(segment);
				}
			}
		}
	}

	private void saveDocuments(ProductOffering productOffering, List<Integer> documentIdList) {
		if (documentIdList != null) {
			for (int documentId : documentIdList) {
				Document document = documentAppService.findById(documentId);
				if (document != null && !productOffering.getDocuments().contains(document)) {
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
		clonedProductOffering.setClonnedProductOffering(childproductOffering);
		clonedProductOffering.setProductOfferingType(childproductOffering.getProductOfferingType());
		clonedProductOffering.setCatalog(childproductOffering.getCatalog());
		clonedProductOffering.setCategory(childproductOffering.getCategory());
		clonedProductOffering.setCreateUserId(childproductOffering.getCreateUserId());
		clonedProductOffering.setDescription(childproductOffering.getDescription());
		List<Integer> documentIds = childproductOffering.getDocuments().stream().map(x -> x.getId())
				.collect(Collectors.toList());
		saveDocuments(clonedProductOffering, documentIds);
		clonedProductOffering.setExternalId(childproductOffering.getExternalId());
		clonedProductOffering.setIsReplicated(childproductOffering.getIsReplicated());
		clonedProductOffering.setName(childproductOffering.getName());
		// clonedProductOffering.setPrices(childproductOffering.getPrices());
		List<ProductOfferingDetermines> productOfferingDetermineses = new ArrayList<ProductOfferingDetermines>();
		if (childproductOffering.getProductOfferingDetermineses() != null) {
			for (ProductOfferingDetermines item : childproductOffering.getProductOfferingDetermineses()) {
				ProductOfferingDetermines newItem = new ProductOfferingDetermines();
				newItem.setCreateUserId(item.getCreateUserId());
				if (item.getProdSpecCharValueUse() != null) {
					ProdSpecCharValueUse prodSpecCharValueUse = prodSpecCharValueUseAppService
							.findById(item.getProdSpecCharValueUse().getId());
					newItem.setProdSpecCharValueUse(prodSpecCharValueUse);
				}
				newItem.setProductOffering(clonedProductOffering);
				ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicAppService
						.findById(item.getProductSpecCharacteristic().getId());
				newItem.setProductSpecCharacteristic(productSpecCharacteristic);
				newItem.setTextValue(item.getTextValue());
				newItem.setUpdateUserId(item.getUpdateUserId());
				productOfferingDetermineses.add(newItem);
			}
		}
		clonedProductOffering.setProductOfferingDetermineses(productOfferingDetermineses);
		ProductOfferingType boChildProductOfferingType = productOfferingTypeRepository.findByIdAndIsDeletedIsFalse(3);// BoChild
		clonedProductOffering.setProductOfferingType(boChildProductOfferingType);
		clonedProductOffering.setProductSpecification(childproductOffering.getProductSpecification());
		clonedProductOffering.setReturnPeriod(childproductOffering.getReturnPeriod());
		List<Integer> saleChannelIds = childproductOffering.getSalesChannels().stream().map(x -> x.getId())
				.collect(Collectors.toList());
		saveSalesChannels(clonedProductOffering, saleChannelIds);
		List<Integer> segmentIds = childproductOffering.getSegments().stream().map(x -> x.getId())
				.collect(Collectors.toList());
		saveSegments(clonedProductOffering, segmentIds);
		// clonedProductOffering.setUnsupportedProductSpecCharValueUseGroups(childproductOffering.getUnsupportedProductSpecCharValueUseGroups());
		clonedProductOffering.setUpdateUserId(childproductOffering.getUpdateUserId());
		// clonedProductOffering.setValidFor(childproductOffering.getValidFor());ctor'da
		// geliyor zaten
		clonedProductOffering.setWarrantyPeriod(childproductOffering.getWarrantyPeriod());

		// productOfferingRepository.save(clonedProductOffering);
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

	private void saveDetermines(ProductOffering productOffering,
			List<ProductOfferingCharValueModel> offeringCharValueModelList) {

		if (offeringCharValueModelList != null && offeringCharValueModelList.size() > 0) {

			for (ProductOfferingCharValueModel model : offeringCharValueModelList) {

				ProductOfferingDetermines determines = new ProductOfferingDetermines();
				ProductOfferingDetermines existingDetermines = new ProductOfferingDetermines();
				boolean determinesExists = false;

				for (ProductOfferingDetermines persistedDetermines : productOffering.getProductOfferingDetermineses()) {
					if (persistedDetermines.getProductSpecCharacteristic().getId() == model.getCharId()) {
						determinesExists = true;
						existingDetermines = persistedDetermines;
						break;
					}
				}

				ProdSpecCharValueUse prodSpecCharValueUse = prodSpecCharValueUseAppService
						.findById(model.getCharValueUseId());
				determines.setProdSpecCharValueUse(prodSpecCharValueUse);
				ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicAppService
						.findById(model.getCharId());
				determines.setProductSpecCharacteristic(productSpecCharacteristic);
				determines.setTextValue(model.getCharValue());
				determines.setProductOffering(productOffering);

				if (determinesExists) {
					productOffering.getProductOfferingDetermineses().remove(existingDetermines);
				}

				productOffering.getProductOfferingDetermineses().add(determines);
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
		List<Integer> simpleProductOfferingIds = productOffering.getProductOfferings().stream().map(x -> x.getId())
				.collect(Collectors.toList());
		return new ProductOfferingDetailModel(productOffering.getId(), productOffering.getName(),
				productOffering.getDescription(), productOffering.getIsReplicated(), productOffering.getIsSellable(),
				productOffering.getProductSpecification() != null ? productOffering.getProductSpecification().getId()
						: 0,
				productOffering.getCatalog() != null ? productOffering.getCatalog().getId() : 0,
				productOffering.getCategory() != null ? productOffering.getCategory().getId() : 0,
				productOffering.getProductOfferingType() != null ? productOffering.getProductOfferingType().getId() : 0,
				productOffering.getProductOfferingTerm() != null ? productOffering.getProductOfferingTerm().getTerm()
						: 0,
				simpleProductOfferingIds != null ? simpleProductOfferingIds : new ArrayList<Integer>(),
				findOfferingCharValues(id), getSalesChannelsIds(productOffering), getSegmentIds(productOffering),
				getDocumentIds(productOffering), findOfferingPrices(productOffering));
	}

	public List<IdNameDescriptionModel> getSimpleOfferingsForSelect() {
		return productOfferingRepository.findAllByProductOfferingTypeIdAndIsDeletedIsFalse(1).stream()
				.map(x -> new IdNameDescriptionModel(x.getId(), x.getName(), x.getDescription()))
				.collect(Collectors.toList());
	}

	public List<ProductOfferingListModel> findAllByProductOfferingTypeId(int productOfferingTypeId) {
		return productOfferingRepository.findAllByProductOfferingTypeIdAndIsDeletedIsFalse(productOfferingTypeId)
				.stream()
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
					model.setCharValue(
							determines.getProdSpecCharValueUse().getProductSpecCharacteristicValue().getValue());
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

	private List<PriceRequest> findOfferingPrices(ProductOffering productOffering) {
		return priceAppService.getAllPrices(productOffering);
	}

	private List<Integer> getSegmentIds(ProductOffering productOffering) {
		// TODO: foreign keys will be persisted for performance
		List<Integer> segmentIds = new ArrayList<>();
		for (Segment segment : productOffering.getSegments()) {
			segmentIds.add(segment.getId());
		}
		return segmentIds;
	}

	private List<Integer> getSalesChannelsIds(ProductOffering productOffering) {
		// TODO: foreign keys will be persisted for performance
		List<Integer> salesChannelsIds = new ArrayList<>();
		for (SalesChannel salesChannel : productOffering.getSalesChannels()) {
			salesChannelsIds.add(salesChannel.getId());
		}
		return salesChannelsIds;
	}

	private List<Integer> getDocumentIds(ProductOffering productOffering) {
		// TODO: foreign keys will be persisted for performance
		List<Integer> documentIds = new ArrayList<>();
		for (Document document : productOffering.getDocuments()) {
			documentIds.add(document.getId());
		}
		return documentIds;
	}
}
