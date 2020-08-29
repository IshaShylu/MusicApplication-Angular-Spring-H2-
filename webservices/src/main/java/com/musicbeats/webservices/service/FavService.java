package com.musicbeats.webservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbeats.webservices.model.favourites;
import com.musicbeats.webservices.repository.FavRepository;

@Service
public class FavService {
	
	@Autowired
	private FavRepository repo;
	
	public void saveFavourites(favourites fav) {
		repo.save(fav);
	}
	public List<favourites> fetchFavByUserId(Long id) {
		return repo.findByUserid(id);
	}
	public favourites fetchUserByUseridAndSongid(Long userid,Long songid) {
		return repo.findByUseridAndSongid(userid,songid);
	}
	public favourites deleteFavById(Long id) {
		favourites fav=repo.getOne(id);
		repo.deleteById(id);
		return fav;
	}
	public void deleteFav(favourites fav) {
		repo.delete(fav);
	}
}
