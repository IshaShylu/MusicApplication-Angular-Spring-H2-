package com.musicbeats.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.musicbeats.webservices.model.admin;
import com.musicbeats.webservices.service.Registration2Service;

@RestController
public class Registration2Controller {
	@Autowired
	private Registration2Service service;
	
	@PostMapping("/registeradmin")
	@CrossOrigin(origins="http://localhost:4200")
	public admin registerAdmin(@RequestBody admin Admin) throws Exception {
		String tempEmailId=Admin.getEmailid();
		if(tempEmailId!=null && !"".equals(tempEmailId)) {
			admin AdminObj=service.fetchAdminByEmailId(tempEmailId);
			if(AdminObj!=null) {
				throw new Exception("Admin with "+tempEmailId+" already exists");
			}
		}
		admin AdminObj=null;
		AdminObj=service.saveAdmin(Admin);
		return AdminObj;
	}
	
	@PostMapping("/loginadmin")
	@CrossOrigin(origins="http://localhost:4200")
	public admin loginAdmin(@RequestBody admin Admin) throws Exception {
		String tempEmailId=Admin.getEmailid();
		String tempPassword=Admin.getPassword();
		admin AdminObj=null;
		if(tempEmailId!=null && tempPassword!=null) {
			AdminObj=service.fetchAdminByEmailIdAndPassword(tempEmailId,tempPassword);
		}
		if(AdminObj==null) {
			throw new Exception("Invalid Credentials");
		}
		return AdminObj;
	}
}
