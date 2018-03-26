package com.ericsson.modernization.services.productcatalog.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryAppService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(CategoryCreateRequest categoryCreateRequest){

        Category category = new Category();
        category.setName(categoryCreateRequest.getName());
        category.setCode(categoryCreateRequest.getCode());
        category.setDescription(categoryCreateRequest.getDescription());
        category.setParentId(category.getParentId());
        category.setIsRoot(categoryCreateRequest.getIsRoot());
/*
        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(categoryCreateRequest.getValidForEndDate());
        validFor.setValidForStartDate(categoryCreateRequest.getValidForStartDate());
        category.setValidFor(validFor);
*/
        return categoryRepository.save(category);
    }

    public void delete(int categoryID){
        Category category = categoryRepository.findById(categoryID).get();
        if(category != null){
            category.setDeleted(true);
            categoryRepository.save(category);
        }
    }

    public Category findById(int id){
        return categoryRepository.findByIdAndIsDeletedIsFalse(id);
    }

    public List<Category> findAll(){
        return categoryRepository.findAllByIsDeletedIsFalse();
    }
}
