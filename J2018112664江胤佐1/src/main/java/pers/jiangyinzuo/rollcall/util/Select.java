package main.java.pers.jiangyinzuo.rollcall.util;

import main.java.pers.jiangyinzuo.rollcall.helper.FileHelper;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;

import java.util.Map;

public class Select {
	public static void printMenu(String[] menuItems) {
		System.out.println("---------------");
		for (String item : menuItems) {
			System.out.println(item);
		}
		System.out.println("---------------");
	}
	
	public static AbstractMenu selectMenu(Map<String, AbstractMenu> selectedMenuMap, String[] menuItems) {
		
		while (true) {

			printMenu(menuItems);
			String line = FileHelper.scanner.nextLine();
			AbstractMenu selectedMenu = selectedMenuMap.get(line);
			if (selectedMenu == null) {
				System.out.println(" ‰»Î”–ŒÛ");
			} else {
				return selectedMenu;
			}
		}
	}
}
