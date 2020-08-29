package com.musicbeats.webservices.model;

import javax.persistence.*;

@Entity
@Table(name="Songs")
public class songs {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String language;
	private String author;
	private String singers;
	private String movie;
	private String category;
	@Column(name="picByte",length=50000000)
	private byte[] picByte;
	public Long getId() {
		return id;
	}
	
	/*public songs() {}

	public songs(Long id, String name, String language, String author, String singers, String movie) {
		super();
		this.id = id;
		this.name = name;
		this.language = language;
		this.author = author;
		this.singers = singers;
		this.movie = movie;
	}*/

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String catg) {
		this.category = catg;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSingers() {
		return singers;
	}
	public void setSingers(String singers) {
		this.singers = singers;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	
	
}
