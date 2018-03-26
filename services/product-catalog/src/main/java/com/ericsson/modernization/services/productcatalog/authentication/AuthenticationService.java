package com.ericsson.modernization.services.productcatalog.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AuthenticationService {
    @Autowired
    private SystemUserRepository userRepository;
    
/*
	@RequestMapping("/api/Authentication/Login")
	public String Login(String userName, String password) {
		return "myToken";
	}

	@RequestMapping("/api/Authentication/Logout")
	public String Logout(String userName) {
		return "Logout";
	}*/
}
