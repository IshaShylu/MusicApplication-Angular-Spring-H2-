package com.musicbeats.webservices.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.musicbeats.webservices.model.history;
import com.musicbeats.webservices.model.songs;

public interface HistoryRepository extends JpaRepository<history, Long>{
	public List<history> findByUserid(Long id);
	
	public history findByUseridAndSongid(Long userid,Long songid);
	
	@Query("select h from history h where h.userid=?1 ORDER BY Date DESC")
	public List<history> findByUseridAndOrderByDate(Long uid);
	
	@Query("update history SET date=?3 where userid=?1 and songid=?2 ")
	public void updateHistoryDate(Long uid,Long sid,Date date);
	
	@Query("select h from songs h where h.id IN(select songid from history GROUP BY songid ORDER BY count(songid) DESC)")
	public List<songs> fetchMostViewed();
}
