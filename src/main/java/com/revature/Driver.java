package com.revature;
import com.revature.controller.*;

public class Driver {

	public static void main(String[] args) {
		MenuController menu = new MenuController();
		System.out.println("Starting...");
		menu.runMenu();
		System.out.println("Ending Program");
	}

}
