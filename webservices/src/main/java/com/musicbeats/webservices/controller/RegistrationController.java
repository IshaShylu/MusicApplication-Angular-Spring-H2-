package com.musicbeats.webservices.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.musicbeats.webservices.model.user;
import com.musicbeats.webservices.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	@CrossOrigin(origins="http://localhost:4200")
	public user registerUser(@RequestBody user User) throws Exception {
		String tempEmailId=User.getEmailid();
		if(tempEmailId!=null && !"".equals(tempEmailId)) {
			user UserObj=service.fetchUserByEmailId(tempEmailId);
			if(UserObj!=null) {
				throw new Exception("User with "+tempEmailId+" already exists");
			}
		}
		user UserObj=null;
		UserObj=service.saveUser(User);
		return UserObj;
	}
	
	@PostMapping("/loginuser")
	@CrossOrigin(origins="http://localhost:4200")
	public user loginUser(@RequestBody user User) throws Exception {
		String tempEmailId=User.getEmailid();
		String tempPassword=User.getPassword();
		user UserObj=null;
		if(tempEmailId!=null && tempPassword!=null) {
			UserObj=service.fetchUserByEmailIdAndPassword(tempEmailId,tempPassword);
		}
		if(UserObj==null) {
			throw new Exception("Invalid Credentials");
		}
		return UserObj;
	}
}
