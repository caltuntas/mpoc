package com.ericsson.modernization.services.productcatalog.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ProductOfferingRepositoryImpl implements ProductOfferingRepositoryCustom{
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Object[] mycustomQuery() {
		// TODO Auto-generated method stub
		return null;
	}   
}