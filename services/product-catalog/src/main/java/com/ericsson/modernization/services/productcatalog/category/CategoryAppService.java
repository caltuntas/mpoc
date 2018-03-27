package com.ericsson.modernization.services.productcatalog.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.modernization.services.productcatalog.category.request.CategoryCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.Category;
import com.ericsson.modernization.services.productcatalog.repository.CategoryRepository;

@Transactional
@Service
public class CategoryAppService {

    @Autowired
    private CategoryRepository repository;

    public Category create(CategoryCreateRequest request){

        Category category = new Category();
        category.setName(request.getName());
        category.setCode(request.getCode());
        category.setDescription(request.getDescription());
        category.setParentId(request.getParentId());
        category.setIsRoot(request.getIsRoot());
/*
        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(request.getValidForEndDate());
        validFor.setValidForStartDate(request.getValidForStartDate());
        category.setValidFor(validFor);
*/
        return repository.save(category);
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
}
