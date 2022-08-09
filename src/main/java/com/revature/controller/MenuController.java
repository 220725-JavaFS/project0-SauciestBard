package com.revature.controller;

import com.revature.Utils.*;
import com.revature.service.*;

//import java.util.List;
import java.util.Scanner;

public class MenuController {

	private Scanner scan = new Scanner(System.in);
	private AccountService accountServ = new AccountService();
	private ChatService chatServ = new ChatService();
	private LogInService loginServ = new LogInService();
	
	private boolean exit = false;
	private String responce;
	

	public void runMenu() {
		System.out.println("Welcome to the chat system");
		while(!exit) {
			if(!User.isLoggedIn()) {
				System.out.println("1: Login");
				//Add new use functions here if time
			}else {
				System.out.println("1: Send Message");
				System.out.println("2: View Messages");
				if(User.getAccountType() == "moderator" || User.getAccountType() == "admin") {
					System.out.println("3: Ban User");
				}
				if(User.getAccountType() == "admin") {
					System.out.println("4: Add moderator");
				}
			}
			System.out.println("0: Exit");
			
			responce = scan.nextLine();
			
			switch(responce) {
			case "0":
				exit = true;
				break;
			case "1":
				if(!User.isLoggedIn())  {
					loginMenu();
				}else {
					System.out.print("\n\n\n\n");
					System.out.print("Enter Message to send: ");
					responce = scan.nextLine();
					chatServ.sendMessage(responce);
				}
				break;
			case "2":
				if(User.isLoggedIn()) {
					System.out.print("\n\n\n" + chatServ.getChatLog() + "\n\n\n");
				}else {
					System.out.print("Input not valid\n\n\n");
				}
				break;
			case "3":
				if(!User.isLoggedIn() || User.getAccountType() == "user") {
					System.out.print("Input not valid\n\n\n");
				}else {
					banMenu();
				}
				break;
			case "4":
				if(User.isLoggedIn() && User.getAccountType() == "admin") {
					addModMenu();
				}else {
					System.out.print("Input not valid\n\n\n");
				}
				break;
			default:
				System.out.print("Input not valid\n\n\n");
				break;
			}
		}
		
		return;
	}
	
	private void loginMenu() {
		String username;
		String password;
		
		while(!User.isLoggedIn()) {
			System.out.print("Username: ");
			username = scan.nextLine();
			System.out.print("Password: ");
			password = scan.nextLine();
			
			if(!loginServ.tryLogin(username, password)) {
				System.out.println("Login failed");
			}
			
		}
		return;
	}
	
	private void banMenu() {
		boolean userExists = false;
		String input = "";
		String username = "";
		String banReason = "";
		int banType = -1;
		int daysToRelease = 0;
		int hoursToRelease = 0;
		
		
		while(!userExists) {
			System.out.print("Enter the username of the user to ban: ");
			input = scan.nextLine();
			userExists = accountServ.checkUsernameExists(input);
			if(!userExists) {
				System.out.println("User does not exist");
			}else {
				username = input;
			}
		}
		
		System.out.print("Enter the reason for the ban");
		banReason = scan.nextLine();
		
		while(banType == -1) {
			System.out.println("Select ban length");
			System.out.println("0: Permenant (no release)");
			System.out.println("1: 1 hour ban");
			System.out.println("2: 1 day ban");
			System.out.println("3: 1 week ban");
			System.out.println("4: 1 month ban");
			//System.out.println("5: Custom ban length");
			
			input = scan.nextLine();
			
			switch (input){
				case "0":
					banType = 1;
					break;
				case "1":
					banType = 2;
					daysToRelease = 0;
					hoursToRelease = 1;
					break;
				case "2":
					banType = 2;
					daysToRelease = 1;
					hoursToRelease = 0;
					break;
				case "3":
					banType = 2;
					daysToRelease = 7;
					hoursToRelease = 0;
					break;
				case "4":
					banType = 2;
					daysToRelease = 30;
					hoursToRelease = 0;
					break;
				default:
					System.out.println("Input not valid");
					break;					
			}
		}
		
		accountServ.ban(username, banReason, banType, daysToRelease, hoursToRelease);
		return;
	}
	
	
	private void addModMenu() {
		boolean userExists = false;
		String input = "";
		String username = "";
		
		while(!userExists) {
			System.out.print("Enter the username of the user to ban: ");
			input = scan.nextLine();
			userExists = accountServ.checkUsernameExists(input);
			if(!userExists) {
				System.out.println("User does not exist");
			}else {
				username = input;
			}
		}
		
		accountServ.addMod(username);
		return;
	}
}
