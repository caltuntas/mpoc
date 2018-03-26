package com.ericsson.modernization.services.productcatalog.authentication;

import java.util.List;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ericsson.modernization.services.productcatalog.applicationservice.request.CatalogCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.Catalog;

@RestController
@RequestMapping("/systemuser")
public class SystemUserRestController {

	@Autowired
	private SystemUserAppService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<SystemUser> login(@RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getUsername() + " " + loginRequest.getPassword() + " " + loginRequest.toString());
		SystemUser user = userService.findByUserNameAndPassword("admin", "12345");
		if (user == null) {
			return new ResponseEntity<SystemUser>(user, HttpStatus.NOT_FOUND);
		}
		user.setPassword(null);
		return new ResponseEntity<SystemUser>(user, HttpStatus.OK);
	}

	@RequestMapping("/getallusers")
	public ResponseEntity<List<SystemUser>> getAllUsers() {
		return new ResponseEntity<List<SystemUser>>(userService.findAll(), HttpStatus.OK);
	}

	@RequestMapping("/users/{id}")
	public ResponseEntity<SystemUser> getUser(@PathVariable String id) {
		return new ResponseEntity<SystemUser>(userService.findById(Integer.parseInt(id)), HttpStatus.OK);
	}

}
