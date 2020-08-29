package com.musicbeats.webservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbeats.webservices.model.songs;
import com.musicbeats.webservices.repository.SongsCrudRepository;

@Service
public class SongsCrudService {
	@Autowired
	private SongsCrudRepository repo;
	
	public List<songs> fetchsongsList(){
		return repo.findAll();
	}
	public void saveSongToDB(songs song) {
		repo.save(song);
	}
	public songs fetchSongById(Long id) {
		return repo.findById(id).get();
	}
	public void updateSongToDB(songs song) {
		repo.save(song);
	}
	
	public List<songs> fetchBylanguage(String lang) {
		return repo.findByLanguage(lang);
	}
	
	public List<songs> fetchBycategory(String cat) {
		return repo.findByCategory(cat);
	}
	public Optional<songs> fetchSongByName(String name) {
		return repo.findByName(name);
	}
	public songs deleteSongById(Long id) {
		songs Song = repo.getOne(id);
		repo.deleteById(id);
		return Song;
	}
	/*public String deleteSongById(int id) {
		String res;
		try {
		//repo.deleteById(id);
		res="Song removed successfully";
		}
		catch (Exception e) {
			// TODO: handle exception
			res="Song removal unsuccessful";
		}
		return res;
	}
	*/
}
