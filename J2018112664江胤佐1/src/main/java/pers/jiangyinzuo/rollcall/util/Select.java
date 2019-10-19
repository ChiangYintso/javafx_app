package main.java.pers.jiangyinzuo.rollcall.util;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;

import java.util.Map;
import java.util.Scanner;

public class Select {
	private static void printMenu(String[] menuItems) {
		System.out.println("---------------");
		for (String item : menuItems) {
			System.out.println(item);
		}
		System.out.println("---------------");
	}
	
	public static AbstractMenu selectMenu(Map<String, AbstractMenu> selectedMenuMap, String[] menuItems) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			printMenu(menuItems);
			AbstractMenu selectedMenu = selectedMenuMap.get(scanner.nextLine());
			if (selectedMenu == null) {
				System.out.println(" ‰»Î”–ŒÛ");
			} else {
				scanner.close();
				return selectedMenu;
			}
		}
	}
}
