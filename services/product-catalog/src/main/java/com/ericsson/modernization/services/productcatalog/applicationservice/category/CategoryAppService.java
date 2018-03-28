package com.ericsson.modernization.services.productcatalog.applicationservice.category;

import java.util.List;
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
public class CategoryAppService {

    @Autowired
    private CategoryRepository repository;

    public Category create(CategoryCreateRequest request){

        Category category = new Category();
        getEntityFromRequest(request, category);
        return repository.save(category);
    }

	private void getEntityFromRequest(CategoryCreateRequest request, Category category) {
		category.setName(request.getName());
        category.setCode(request.getCode());
        category.setDescription(request.getDescription());
        category.setParentId(request.getParentId());
/*
        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(request.getValidForEndDate());
        validFor.setValidForStartDate(request.getValidForStartDate());
        category.setValidFor(validFor);
*/
	}
    
    public void update(CategoryCreateRequest request){

    	Category category = repository.findByIdAndIsDeletedIsFalse(request.getId());
        getEntityFromRequest(request, category);
        repository.save(category);
    }

    public void delete(int id){
        Category category = repository.findById(id).get();
        if(category != null){
            category.setDeleted(true);
            repository.save(category);
        }
    }

    public Category findById(int id){
        return repository.findByIdAndIsDeletedIsFalse(id);
    }

    public List<Category> findAll(){
        return repository.findAllByIsDeletedIsFalse();
    }

	public List<CategoryListModel> findAllWithModel() {
		return repository.findAllByIsDeletedIsFalse().stream()
				.map(x -> new CategoryListModel(
						x.getId(), 
						x.getCode(), 
						x.getName(), 
						x.getDescription(),
						x.getParentId() != 0 ? repository.findByIdAndIsDeletedIsFalse(x.getParentId()).getCode() : null)
				).collect(Collectors.toList());
	}
}
