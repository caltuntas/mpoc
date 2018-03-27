package com.ericsson.modernization.services.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByIdAndIsDeletedIsFalse(int id);
    List<Category> findAllByIsDeletedIsFalse();
}
