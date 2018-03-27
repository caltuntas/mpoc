package com.ericsson.modernization.services.productcatalog.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Integer>
{
	SystemUser findByUserNameAndPassword(String username, String password);
}
