package com.revature.service;

import com.revature.Utils.*;
import com.revature.repository.*;
import java.util.*;

public class AccountService {
	
	static Dao dao = new DaoImp();
	
	public static boolean checkUsernameExists(String username) {
		return dao.userExists(username);
	}
	
	
	public static void ban(String username, String reason, int banState, int banDays, int banHours) {
		Calendar release = Calendar.getInstance();
		release.add(Calendar.DATE, banDays);
		release.add(Calendar.HOUR_OF_DAY, banHours);
		dao.addBan(username, User.getUsername(), reason, banState, release.getTime());
		return;
	}
	
	public static void addMod(String username) {
		if(dao.userExists(username)) {
			dao.setAccountType(username, "moderator");
		}
		return;
	}
}
