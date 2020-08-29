package com.musicbeats.webservices.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.musicbeats.webservices.model.songs;
import com.musicbeats.webservices.service.SongsCrudService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SongsCrudController {
	
	private byte[] bytes;
	
	@Autowired
	private SongsCrudService service;
	
	
	//@RequestMapping(path="/getsongslist",method=RequestMethod.GET)
	@GetMapping(path="/getsongslist")
	public List<songs> fetchSongsList(){
		List<songs> songslist = new ArrayList<songs>();
		songslist=service.fetchsongsList();
		return songslist;
	}
	
	@GetMapping(path="/getbylanguage/{lang}")
	public List<songs> fetchSongsByLanguage(@PathVariable String lang){
		List<songs> songslist = new ArrayList<songs>();
		songslist=service.fetchBylanguage(lang);
		return songslist;
	}
	
	@GetMapping(path="/getbycategory/{cat}")
	public List<songs> fetchSongsByCategory(@PathVariable String cat){
		List<songs> songslist = new ArrayList<songs>();
		songslist=service.fetchBycategory(cat);
		return songslist;
	}
	
	@GetMapping(path="/getbyid/{id}")
	public songs fetchSongById(@PathVariable long id){
		return service.fetchSongById(id);
	}
	
	@GetMapping(path="/viewsongs/{id}")
	public songs viewSong(@PathVariable("id") long id){
		return service.fetchSongById(id);
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}
	
	@PostMapping(path="/addsong")
	public void createSong(@RequestBody songs Song){
		Song.setPicByte(this.bytes);
		service.saveSongToDB(Song);
		this.bytes=null; 
		
	}
	
	@PutMapping("/update")
	public void updateSong(@RequestBody songs Song) {
		service.updateSongToDB(Song);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public songs deleteSong(@PathVariable("id") long id) {
		return service.deleteSongById(id);
	}
	
	@GetMapping(path="/getsongbyname/{name}")
	public songs fetchSongByName(@PathVariable String name){
		return service.fetchSongByName(name).get();
	}
	
	/*@DeleteMapping(path="/deletesongbyname/{name}")
	public String deleteSongByName(@PathVariable String name){
		Long tempid=service.fetchSongByName(name).get().getId();
		return service.deleteSongById(tempid);
	}
	*/
	
}

