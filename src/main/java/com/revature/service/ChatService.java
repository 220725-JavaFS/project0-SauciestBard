package com.revature.service;

import com.revature.repository.*;
import com.revature.Utils.*;

public class ChatService {
	
	Dao dao = new DaoImp();
	
	public void sendMessage(String message) {
		if(User.isLoggedIn() && !dao.isBanned(User.getUsername())) {
			dao.sendMessage(User.getUsername(), message);
		}
		return;
	}
	
	public String getChatLog() {
		return dao.getMessages();
	}
}
