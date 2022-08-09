package com.revature.repository;

import java.util.*;

public interface Dao {
	
	public String getPassHash(String username);
	
	public String getUserRole(String username); 
	
	public void sendMessage(String username, String message);
	
	public boolean isBanned(String username);
	
	public String getMessages();
	
	public boolean userExists(String username);
	
	public void addBan(String bannedUser, String banningMod, String banReason, int banState, Date releaseDate);
	
	public void setAccountType(String username, String newUserRole);

}
