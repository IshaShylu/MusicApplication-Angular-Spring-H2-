package com.musicbeats.webservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicbeats.webservices.model.favourites;

public interface FavRepository extends JpaRepository<favourites, Long>{
	public List<favourites> findByUserid(Long id);
	
	public favourites findByUseridAndSongid(Long userid,Long songid);

}
