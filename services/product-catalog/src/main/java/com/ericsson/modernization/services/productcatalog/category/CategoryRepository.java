package com.ericsson.modernization.services.productcatalog.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByIdAndIsDeletedIsFalse(int id);
    List<Category> findAllByIsDeletedIsFalse();
}
