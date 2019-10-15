package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.util;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;

import java.util.Map;
import java.util.Scanner;

public class Select {
	public static AbstractMenu selectMenu(Map<String, AbstractMenu> selectMap) {
		Scanner scanner = new Scanner(System.in);
		AbstractMenu selectedMenu =  selectMap.get(scanner.nextLine());
		scanner.close();
		return selectedMenu;
	}
}
