package com.ericsson.modernization.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
/*
	@RequestMapping("/getallusers")
	public ResponseEntity<List<SystemUser>> getAllUsers() {
		return new ResponseEntity<List<SystemUser>>(userService.findAll(), HttpStatus.OK);
	}

	@RequestMapping("/users/{id}")
	public ResponseEntity<SystemUser> getUser(@PathVariable String id) {
		return new ResponseEntity<SystemUser>(userService.findById(Integer.parseInt(id)), HttpStatus.OK);
	}*/

}
