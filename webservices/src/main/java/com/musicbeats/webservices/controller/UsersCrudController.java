package com.musicbeats.webservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.musicbeats.webservices.model.user;
import com.musicbeats.webservices.service.RegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsersCrudController {
	@Autowired
	private RegistrationService service;
	
	@GetMapping(path="/getuserslist")
	public List<user> fetchUsersList(){
		List<user> userslist = new ArrayList<user>();
		userslist=service.getUsers();
		return userslist;
	}
	@PostMapping(path="/adduser")
	public void createUser(@RequestBody user User){
		service.saveUser(User);
	}
	
	@DeleteMapping(path = { "delete/{id}" })
	public user deleteUser(@PathVariable("id") int id) {
		return service.deleteUserById(id);
	}
}
