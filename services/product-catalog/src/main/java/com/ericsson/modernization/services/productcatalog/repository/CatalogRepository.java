package com.ericsson.modernization.services.productcatalog.repository;
import com.ericsson.modernization.services.productcatalog.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    Catalog findByIdAndIsDeletedIsFalse(int id);
    List<Catalog> findAllByIsDeletedIsFalse();
}
