package com.ericsson.modernization.services.productcatalog.rest.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ericsson.modernization.services.productcatalog.category.CategoryAppService;
import com.ericsson.modernization.services.productcatalog.category.request.CategoryCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.Category;

import java.util.List;

@RestController
@RequestMapping("/category")
//https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/
public class CategoryRestController {
	@Autowired
	private CategoryAppService categoryAppService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createCategory(@RequestBody CategoryCreateRequest createRequest) {
		Category category = categoryAppService.create(createRequest);
		String message = "A category with id : " + category.getId() + " is created";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAll() {
		List<Category> categories = categoryAppService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		categoryAppService.delete(id);
		String message = "The category with id : " + id + " is deleted.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
