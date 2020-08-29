package com.musicbeats.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicbeats.webservices.model.admin;

public interface Registration2Repository extends JpaRepository<admin, Integer>  {
	public admin findByEmailid(String emailid);
	public admin findByEmailidAndPassword(String emailid,String password);
}

