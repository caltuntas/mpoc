package com.ericsson.modernization.services.productcatalog.applicationservice.category;

import java.util.List;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.request.CategoryCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.response.CategoryListModel;
import com.ericsson.modernization.services.productcatalog.model.Category;

public interface CategoryService {
	Category create(CategoryCreateRequest request);

	void getEntityFromRequest(CategoryCreateRequest request, Category category);

	void update(CategoryCreateRequest request);

	void delete(int id);

	Category findById(int id);

	List<Category> findAll();

	List<CategoryListModel> findAllWithModel();

	List<CategoryListModel> getLeavesFullPathNames();

	List<CategoryListModel> getLeavesFullPathNamesByCategories(List<Category> categories);
}
