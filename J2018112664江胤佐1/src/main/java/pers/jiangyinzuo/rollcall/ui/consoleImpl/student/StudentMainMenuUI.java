package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student;

import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.ui.UI;

public class StudentMainMenuUI implements UI {
	public MENU showUI() {
		Scanner scanner = new Scanner(System.in);
		int option;
		while (true) {
			try {
				System.out.println("---------------");
				System.out.println("1. �鿴�α�");
				System.out.println("2. �鿴��ѧ��");
				System.out.println("3. �鿴������¼");
				System.out.println("4. �˳�");
				System.out.println("---------------");
				option = scanner.nextInt();
				switch (option) {
				case 1:
					return MENU.SCHEDULE;
				case 4:
					scanner.close();
					return MENU.EXIT;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
