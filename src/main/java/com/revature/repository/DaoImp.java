package com.revature.repository;

import java.util.Date;

public class DaoImp implements Dao {

	@Override
	public String getPassHash(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserRole(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(String username, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBanned(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addBan(String bannedUser, String banningMod, String banReason, int banState, Date releaseDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAccountType(String username, String newUserRole) {
		// TODO Auto-generated method stub
		
	}

}
