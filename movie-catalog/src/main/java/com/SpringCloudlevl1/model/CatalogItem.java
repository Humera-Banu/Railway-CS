package com.SpringCloudlevl1.model;

public class CatalogItem {
	private String name;
	private int movieId;
	private String description;
	private int rating;
	
	public CatalogItem(String name, String description, int rating, int movieId) {
		this.name = name;
		this.movieId = movieId;
		this.description = description;
		this.rating = rating;
	}
	public CatalogItem(String name, String description, int rating) {
		this.name = name;
		this.description = description;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getMovieId() {
		return movieId;
	}
	/*
	 * public void setMovieId(int movieId) { this.movieId = movieId; }
	 */
	
	
}
