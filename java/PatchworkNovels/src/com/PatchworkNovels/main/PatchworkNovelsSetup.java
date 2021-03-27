package com.PatchworkNovels.main;

import java.util.Scanner;

import com.PatchworkNovels.dao.AbstractDAO;
import com.PatchworkNovels.service.*;

public class PatchworkNovelsSetup extends AbstractDAO {
	
	// initial prompt menu
	public static void checkDB(Scanner scanner) {
		System.out.println("Would you like to load the database using the given SQL files?");
		System.out.println("1. Yes, (re)create the database using fresh values.");
		System.out.println("2. No, continue to the program.");
		String input = "";
		int choice = -1;
		while(true) {
			input = scanner.nextLine();
			if(!input.matches("\\d+")) {
				System.out.println("Not an option. Please, enter 1 or 2.");
				continue;
			}
			choice = Integer.parseInt(input.replaceAll("\\D+", ""));
			switch(choice) {
			case 1:
				System.out.println("Loading database...");
				if(loadDB(scanner) && createTables() && loadTables()) {
					closeJDBC();
					System.out.println("Database loaded successfully, proceeding to program.");
					return;
				}
				System.out.println("There was an error in loading the database.");
			case 2:
				System.out.println("Proceeding to program without loading database.");
				return;
			default:
				System.out.println("Not an option. Please, enter 1 or 2.");
				break;
			}
		}
	}
	
	// attempts to create the database if it does not yet exist
	private static boolean loadDB(Scanner scanner) {
		while(true) {
			int databaseType = -1;
			while(true) {
				System.out.print("What database do you use?\n1. MariaDB\n2. MySQL\n");
				String input = scanner.nextLine();
				if(!input.matches("\\d+")) {
					System.out.println("Not an option. Please, enter 1 or 2.");
					continue;
				}
				databaseType = Integer.parseInt(input.replaceAll("\\D+", ""));
				if(databaseType == 1 || databaseType == 2) {
					break;
				} else {
					System.out.println("Not an option. Please, enter 1 or 2.");
				}
			}
			System.out.println("Please enter your database username.");
			String user = scanner.nextLine();
			System.out.println("Please enter your database password.");
			String pass = scanner.nextLine();
			if(startJDBC(databaseType, user, pass)) return true;
			while(true) {
				System.out.print("Continue trying?\n1. Yes\n2. No\n");
				String input = scanner.nextLine();
				if(!input.matches("\\d+")) {
					System.out.println("Not an option. Please, enter 1 or 2.");
					continue;
				}
				int choice = Integer.parseInt(input.replaceAll("\\D+", ""));
				if(choice == 1) break;
				return false;
			}
		}
	}
	
	// creates the tables using JDBC
	private static boolean createTables() {
		return (new UserService().createTable() && new StoryService().createTable() && new SnippetService().createTable() && new CommentService().createTable());
	}
	
	// reads each included .sql file and runs the SQL queries they contain
	private static boolean loadTables() {
		try {
			String path = "./resources/sql/";
			return runSQLFile(path + "user.sql") && runSQLFile(path + "story.sql") && runSQLFile(path + "snippet.sql");
		} catch(Exception e) {
			System.out.println("Unexpected error while reading in files.");
			e.printStackTrace();
		}
		return false;
	}
}
