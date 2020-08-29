package com.musicbeats.webservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musicbeats.webservices.model.user;
import com.musicbeats.webservices.repository.RegistrationRepository;


@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository repo;
	
	public user saveUser(user User) {
		return repo.save(User);
	}
	public List<user> getUsers() {
		return repo.findAll();
	}
	
	public user fetchUserByEmailId(String emailid) {
		return repo.findByEmailid(emailid);
	}
	
	public user fetchUserByEmailIdAndPassword(String emailid,String password) {
		return repo.findByEmailidAndPassword(emailid,password);
	}
	public user deleteUserById(int id) {
		user User = repo.getOne(id);
		repo.deleteById(id);
		return User;
	}
}
