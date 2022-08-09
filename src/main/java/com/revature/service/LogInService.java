package com.revature.service;

import com.revature.Utils.*;
import com.revature.repository.*;

public class LogInService {
	
	private Dao dao = new DaoImp();
	
	public boolean tryLogin(String username, String password) {
		if(!AccountService.checkUsernameExists(username)) {
			return false;
		}
		
		String passwordHash = hashPassword(password);
		
		String expectedHash = dao.getPassHash(username);
		
		if(passwordHash != expectedHash) {
			return false;
		}
		User.setUsername(username);
		User.setAccountType(dao.getUserRole(username));
		
		User.setLoggedIn(true);
		return true;
	}
	
	
	private String hashPassword(String password) {
		return Integer.toString(password.hashCode());
	}
}
