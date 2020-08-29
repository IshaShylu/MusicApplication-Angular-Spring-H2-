package com.musicbeats.webservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbeats.webservices.model.admin;
import com.musicbeats.webservices.repository.Registration2Repository;

@Service
public class Registration2Service {
	@Autowired
	private Registration2Repository repo;
	
	public admin saveAdmin(admin Admin) {
		
		return repo.save(Admin);
	}
	
	public admin fetchAdminByEmailId(String emailid) {
		return repo.findByEmailid(emailid);
	}
	
	public admin fetchAdminByEmailIdAndPassword(String emailid,String password) {
		return repo.findByEmailidAndPassword(emailid,password);
	}
}
