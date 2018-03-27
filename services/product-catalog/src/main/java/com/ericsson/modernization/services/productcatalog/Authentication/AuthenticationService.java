package com.ericsson.modernization.services.productcatalog.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/auth")
public class AuthenticationService {
	@Autowired
	private SystemUserAppService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<SystemUser> login(@RequestBody LoginRequest loginRequest) {
		SystemUser user = userService.findByUserNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		if (user == null) {
			return new ResponseEntity<SystemUser>(user, HttpStatus.OK);
		}
		user.setPassword(null);
		return new ResponseEntity<SystemUser>(user, HttpStatus.OK);
	}
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
