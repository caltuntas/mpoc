package com.ericsson.modernization.services.productcatalog.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemuser")
public class SystemUserRestController {

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

	@RequestMapping()
	public ResponseEntity<List<SystemUser>> getAllUsers() {
		return new ResponseEntity<List<SystemUser>>(userService.findAll(), HttpStatus.OK);
	}

	@RequestMapping("/users/{id}")
	public ResponseEntity<SystemUser> getUser(@PathVariable String id) {
		return new ResponseEntity<SystemUser>(userService.findById(Integer.parseInt(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		//userService.delete(id);
		String message = "The category with id : " + id + " is deleted.";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
