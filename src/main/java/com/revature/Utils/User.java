package com.revature.Utils;

public class User {
	
	public User(){
		super();
		username = null;
		accountType = null;
	}
	
	public User(String newUsername, String newAccountType){
		super();
		username = newUsername;
		accountType = newAccountType;
	}
	
	
	
	private String username;
	private String accountType;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
