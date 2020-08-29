package com.musicbeats.webservices.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musicbeats.webservices.model.history;
import com.musicbeats.webservices.model.songs;
import com.musicbeats.webservices.repository.HistoryRepository;

@Service
public class HistoryService {
	@Autowired
	private HistoryRepository repo;
	
	public void saveHistory(history hst) {
		repo.save(hst);
	}
	
	public List<history> fetchHistoryByUserId(Long id) {
		return repo.findByUserid(id);
	}
	
	public history fetchByUseridAndSongid(Long userid,Long songid) {
		return repo.findByUseridAndSongid(userid,songid);
	}
	
	public void deleteHistory(history hist) {
		repo.delete(hist);
	}
	
	public List<history> fetchHistoryByIdAndDate(Long uid){
		return repo.findByUseridAndOrderByDate(uid);
	}
	
	public void updateHistoryDate(Long userid,Long songid,Date date) {
		repo.updateHistoryDate(userid,songid,date);
	}
	
	public List<songs> fetchMostViewed(){
		return repo.fetchMostViewed();
	}
}
