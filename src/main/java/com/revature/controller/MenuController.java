package com.revature.controller;

import com.revature.Utils.*;
import com.revature.service.*;

//import java.util.List;
import java.util.Scanner;

public class MenuController {

	private Scanner scan = new Scanner(System.in);
	private ChatService chatServ = new ChatService();
	private LogInService loginServ = new LogInService();
	
	private boolean exit = false;
	private String responce;
	

	public void runMenu() {
		User.setLoggedIn(false);
		System.out.println("Welcome to the chat system");
		while(!exit) {
			if(!User.isLoggedIn()) {
				System.out.println("1: Login");
				System.out.println("2: New User");
			}else {
				System.out.println("1: Send Message");
				System.out.println("2: View Messages");
				if(User.getAccountType().equals("moderator")|| User.getAccountType().equals("admin")) {
					System.out.println("3: Ban User");
				}
				if(User.getAccountType().equals("admin")) {
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
					newUser();
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
				if(User.isLoggedIn() && User.getAccountType().equals("admin")) {
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
	
	private void newUser() {
		String username;
		String password;
		boolean username_valid = false;
		
		while(!User.isLoggedIn()) {
			do {
				System.out.print("Username: ");
				username = scan.nextLine();
				username_valid = !AccountService.checkUsernameExists(username);
				if(!username_valid) {
					System.out.println("Username taken");
				}
			}while(!username_valid);
			
			System.out.print("Password: ");
			password = scan.nextLine();
			
			loginServ.newUser(username, password);
			//loginServ.tryLogin(username, password);
			
			
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
			userExists = AccountService.checkUsernameExists(input);
			if(!userExists) {
				System.out.println("User does not exist");
			}else {
				username = input;
			}
		}
		
		System.out.print("Enter the reason for the ban: ");
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
					banType = 2;
					break;
				case "1":
					banType = 1;
					daysToRelease = 0;
					hoursToRelease = 1;
					break;
				case "2":
					banType = 1;
					daysToRelease = 1;
					hoursToRelease = 0;
					break;
				case "3":
					banType = 1;
					daysToRelease = 7;
					hoursToRelease = 0;
					break;
				case "4":
					banType = 1;
					daysToRelease = 30;
					hoursToRelease = 0;
					break;
				default:
					System.out.println("Input not valid");
					break;					
			}
		}
		
		AccountService.ban(username, banReason, banType, daysToRelease, hoursToRelease);
		return;
	}
	
	
	private void addModMenu() {
		boolean userExists = false;
		String input = "";
		String username = "";
		
		while(!userExists) {
			System.out.print("Enter the username of the user to make a mod: ");
			input = scan.nextLine();
			userExists = AccountService.checkUsernameExists(input);
			if(!userExists) {
				System.out.println("User does not exist");
			}else {
				username = input;
			}
		}
		
		AccountService.addMod(username);
		return;
	}
}
