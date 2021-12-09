package com.SpringCloudlevl1.model;

import java.util.List;

public class UserRating {
	private String userId;
	private List<Ratings> rating;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Ratings> getRating() {
		return rating;
	}

	public void setRating(List<Ratings> rating) {
		this.rating = rating;
	}	 	 

}
