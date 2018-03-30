package com.ericsson.modernization.services.productcatalog.rest.category;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ericsson.modernization.services.productcatalog.applicationservice.category.CategoryService;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.request.CategoryCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.category.response.CategoryListModel;
import com.ericsson.modernization.services.productcatalog.model.Category;

@RestController
@RequestMapping("/categories")
// https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/
public class CategoryRestController {
	@Autowired
	private CategoryService appService;
	/*
	 * @RequestMapping(method = RequestMethod.GET) public
	 * ResponseEntity<List<Category>> getAll() { List<Category> categories =
	 * appService.findAll(); return new ResponseEntity<>(categories, HttpStatus.OK);
	 * }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public List<CategoryListModel> getAll() {
		List<CategoryListModel> categories = appService.findAllWithModel();
		return categories;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category getById(@PathVariable int id) {
		Category category = appService.findById(id);
		return category;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody CategoryCreateRequest request) {
		appService.create(request);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateCategory(@PathVariable int id, @RequestBody CategoryCreateRequest request) {
		appService.update(request);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable int id) {
		appService.delete(id);
	}

	@RequestMapping(value = "/leavesFullPathNames", method = RequestMethod.GET)
	public List<CategoryListModel> getLeavesFullPathNames() {
		List<CategoryListModel> fullList = appService.getLeavesFullPathNames();
		return fullList;
	}
}
