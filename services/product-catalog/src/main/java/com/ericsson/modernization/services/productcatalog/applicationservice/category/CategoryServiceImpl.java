package com.ericsson.modernization.services.productcatalog.applicationservice.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.modernization.services.productcatalog.applicationservice.category.request.CategoryCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.response.CategoryListModel;
import com.ericsson.modernization.services.productcatalog.model.Category;
import com.ericsson.modernization.services.productcatalog.repository.CategoryRepository;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category create(CategoryCreateRequest request) {

		Category category = new Category();
		getEntityFromRequest(request, category);
		return repository.save(category);
	}

	public void getEntityFromRequest(CategoryCreateRequest request, Category category) {
		category.setName(request.getName());
		category.setCode(request.getCode());
		category.setDescription(request.getDescription());
		category.setParentId(request.getParentId());
		/*
		 * TimePeriod validFor = new TimePeriod();
		 * validFor.setValidForEndDate(request.getValidForEndDate());
		 * validFor.setValidForStartDate(request.getValidForStartDate());
		 * category.setValidFor(validFor);
		 */
	}

	public void update(CategoryCreateRequest request) {

		Category category = repository.findByIdAndIsDeletedIsFalse(request.getId());
		getEntityFromRequest(request, category);
		repository.save(category);
	}

	public void delete(int id) {
		Category category = repository.findById(id).get();
		if (category != null) {
			category.setDeleted(true);
			repository.save(category);
		}
	}

	public Category findById(int id) {
		return repository.findByIdAndIsDeletedIsFalse(id);
	}

	public List<Category> findAll() {
		return repository.findAllByIsDeletedIsFalse();
	}

	public List<CategoryListModel> findAllWithModel() {
		return repository.findAllByIsDeletedIsFalse().stream().map(x -> new CategoryListModel(x.getId(), x.getCode(),
				x.getName(), x.getDescription(), x.getParentId(),
				x.getParentId() != 0 ? repository.findByIdAndIsDeletedIsFalse(x.getParentId()).getName() : null))
				.collect(Collectors.toList());
	}

	public Map<String, String> getLeavesFullPathNames() {
		List<Category> categories = repository.findAllByIsDeletedIsFalse();
		return getLeavesFullPathNamesByCategories(categories);
	}

	public Map<String, String> getLeavesFullPathNamesByCategories(List<Category> categories) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			List<Category> children = categories.stream().filter(p -> p.getParentId() == category.getId())
					.collect(Collectors.toList());
			if (children == null)
				continue;
			StringBuilder sb = new StringBuilder();
			Category parent = categories.stream().filter(p -> p.getId() == category.getParentId()).findFirst()
					.orElse(null);
			if (parent != null) {
				Category parent2 = categories.stream().filter(p -> p.getId() == parent.getParentId()).findFirst()
						.orElse(null);
				if (parent2 != null) {
					Category parent3 = categories.stream().filter(p -> p.getId() == parent2.getParentId()).findFirst()
							.orElse(null);
					if (parent3 != null)
						sb.append(parent3.getName() + " - ");
				}
				if (parent2 != null)
					sb.append(parent2.getName() + " - ");
			}
			if (parent != null)
				sb.append(parent.getName() + " - ");
			sb.append(category.getName());
			map.put(category.getId() + "", sb.toString());
		}

		return map;
	}

}
