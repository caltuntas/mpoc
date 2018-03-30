package com.ericsson.modernization.services.productcatalog.repository.util;

import java.util.List;

import com.ericsson.modernization.services.productcatalog.model.Category;

public interface ICategoryDAO {
	List<Category> searchCategory(List<SearchCriteria> params);

	void save(Category entity);
}
