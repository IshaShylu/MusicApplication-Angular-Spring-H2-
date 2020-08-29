package com.musicbeats.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.musicbeats.webservices.model.favourites;
import com.musicbeats.webservices.service.FavService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FavController {
	
	@Autowired
	private FavService service;
	
	@PostMapping(path="/addfavourite")
	public void createFav(@RequestBody favourites fav){
		service.saveFavourites(fav);
	}

	@GetMapping(path="/getfavbyuserid/{id}")
	public List<favourites> getFavByUserId(@PathVariable("id") long id){
		return service.fetchFavByUserId(id);
	}
	
	@PostMapping(path="/checkfavexists")
	public boolean checkFavExists(@RequestBody favourites fav) {
		favourites Fav=new favourites();
		Fav=service.fetchUserByUseridAndSongid(fav.getUserid(), fav.getSongid());
		if(Fav==null) {
			return false;
		}
		return true;
	}
	
	@DeleteMapping(path ="/deletefav/{uid}/{sid}")
	public boolean deleteFav(@PathVariable("uid") long userid,@PathVariable("sid") long songid ) {
		favourites Fav=new favourites();
		Fav=service.fetchUserByUseridAndSongid(userid,songid);
		if(Fav==null) {
			return false;
		}
		service.deleteFav(Fav);
		return true;
	}
}
