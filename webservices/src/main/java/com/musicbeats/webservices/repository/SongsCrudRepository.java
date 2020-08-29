package com.musicbeats.webservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicbeats.webservices.model.songs;

public interface SongsCrudRepository extends JpaRepository<songs, Long>{

	public Optional<songs> findByName(String name);

	public void deleteByName(String name); 
	
	public List<songs> findByLanguage(String lang);
	
	public List<songs> findByCategory(String cat);
}
