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
		
		if(!passwordHash.equals(expectedHash)) {
			return false;
		}
		User.setUsername(username);
		User.setAccountType(dao.getUserRole(username));
		
		User.setLoggedIn(true);
		return true;
	}
	
	public void newUser(String username, String password) {
		dao.addUser(username, hashPassword(password), 0);
		tryLogin(username,password);
		return;
	}
	
	
	private String hashPassword(String password) {
		return Integer.toString(password.hashCode());
	}
}
