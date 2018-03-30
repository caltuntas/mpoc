package com.ericsson.modernization.services.productcatalog.repository.util;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import com.ericsson.modernization.services.productcatalog.model.Category;

@Repository
public class CategoryDAO implements ICategoryDAO {
 
    @PersistenceContext
    private EntityManager entityManager;
 
    @Override
    public List<Category> searchCategory(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root r = query.from(Category.class);
 
        Predicate predicate = builder.conjunction();
 
        for (SearchCriteria param : params) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate, 
                  builder.greaterThanOrEqualTo(r.get(param.getKey()), 
                  param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate, 
                  builder.lessThanOrEqualTo(r.get(param.getKey()), 
                  param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase(":")) {
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate, 
                      builder.like(r.get(param.getKey()), 
                      "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate, 
                      builder.equal(r.get(param.getKey()), param.getValue()));
                }
            }
        }
        query.where(predicate);
 
        List<Category> result = entityManager.createQuery(query).getResultList();
        return result;
    }
 
    @Override
    public void save(Category entity) {
        entityManager.persist(entity);
    }
}