package main.java.ui.consoleImpl;

import java.util.Scanner;

import main.java.ui.MainUI;

public class MainUIConsoleImpl implements MainUI {
	
	public void showLoginUI() {
		System.out.println("��ӭ, �������˺ź�����, ������#�˳�����:");
		
		String id = "";
		String pwd = "";

		try(Scanner scanner = new Scanner(System.in)) {
			while (true) {
				id = scanner.nextLine();
				if (id.equals("#")) {
					return;
				}
				pwd = scanner.nextLine();
				if (pwd.equals("#")) {
					return;
				}
			}
		}
		

	}
}
