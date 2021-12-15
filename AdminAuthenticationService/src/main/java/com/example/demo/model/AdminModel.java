package com.example.demo.model;
 import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

 @Document(collection="admin")
public class AdminModel {
	
	@Id
	private String Id;
	private String username;
	private String password;
	
	public AdminModel() {
		
	}
	public String getUsername() {
		return username;
	}
	public  void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return Id;
	}
	
	
}
 
 
 
 
 
 
 
