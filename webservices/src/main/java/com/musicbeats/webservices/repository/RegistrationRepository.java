package com.musicbeats.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicbeats.webservices.model.user;

public interface RegistrationRepository extends JpaRepository<user, Integer> {
	public user findByEmailid(String emailid);
	public user findByEmailidAndPassword(String emailid,String password);
}
