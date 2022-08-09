package com.revature.service;

import com.revature.Utils.*;

public class LogInService {
	public boolean tryLogin(String username, String password) {
		User.setLoggedIn(true);
		return true;
	}
}
