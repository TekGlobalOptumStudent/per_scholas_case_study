package com.PatchworkNovels.main;

import java.util.Scanner;

public class PatchworkNovelsRunner {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		PatchworkNovelsSetup.checkDB(scanner);
		scanner.close();
	}

}
