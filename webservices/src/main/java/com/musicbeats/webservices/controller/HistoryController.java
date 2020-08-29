package com.musicbeats.webservices.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.musicbeats.webservices.model.history;
import com.musicbeats.webservices.model.songs;
import com.musicbeats.webservices.service.HistoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {
	
	@Autowired
	private HistoryService service;
	
	@PostMapping(path="/addtohistory")
	public void createHistory(@RequestBody history hist){
		hist.setDate(new Date());
		service.saveHistory(hist);
	}
	@GetMapping(path="/historybyuserid/{id}")
	public List<history> getHistoryByUserId(@PathVariable("id") long id){
		return service.fetchHistoryByUserId(id);
	}
	
	@PostMapping(path="/checkhistoryexists")
	public boolean checkHistoryExists(@RequestBody history hist) {
		history hst=new history();
		hst=service.fetchByUseridAndSongid(hist.getUserid(), hist.getSongid());
		if(hst==null) {
			return false;
		}
		return true;
	}
	@DeleteMapping(path ="/deletehistory/{uid}/{sid}")
	public boolean deleteHistory(@PathVariable("uid") long userid,@PathVariable("sid") long songid ) {
		history hst=new history();
		hst=service.fetchByUseridAndSongid(userid, songid);
		/*if(hst==null) {
			return false;
		}*/
		service.deleteHistory(hst);
		return true;
	}
	
	@GetMapping(path="/historyorderbydate/{uid}")
	public List<history> getHistoryByOrder(@PathVariable("uid") long id){
		return service.fetchHistoryByIdAndDate(id);
	}
	
	@GetMapping(path="/mostviewed")
	public List<songs> getMostViewed(){
		return service.fetchMostViewed();
	}
	@PutMapping(path="/updatehistory/{uid}/{sid}")
	public void updateHistory(@PathVariable("uid") long userid,@PathVariable("sid") long songid) {
		service.updateHistoryDate(userid,songid,new Date());
	}

}
