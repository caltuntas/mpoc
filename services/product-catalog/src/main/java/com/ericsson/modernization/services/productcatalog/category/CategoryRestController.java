package com.ericsson.modernization.services.productcatalog.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
	@Autowired
	private CategoryAppService categoryAppService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createCategory(@RequestBody CategoryCreateRequest createRequest) {
		Category category = categoryAppService.create(createRequest);
		String message = "A category with id : " + category.getId() + " is created";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAll() {
		List<Category> categories = categoryAppService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		categoryAppService.delete(id);
		String message = "The category with id : " + id + " is deleted.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
