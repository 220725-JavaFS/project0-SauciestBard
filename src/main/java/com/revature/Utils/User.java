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
	
	
	
	private static String username;
	private static String accountType;
	private static boolean loggedIn = false;
	
	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String newUsername) {
		newUsername = username;
	}

	public static String getAccountType() {
		return accountType;
	}

	public static void setAccountType(String newAccountType) {
		newAccountType = accountType;
	}

	public static boolean isLoggedIn() {
		return loggedIn;
	}

	public static void setLoggedIn(boolean loggedIn) {
		User.loggedIn = loggedIn;
	}
	
	
	
}
