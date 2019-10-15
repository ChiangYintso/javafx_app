package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common;

import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;

public class RollCallUI extends UI {

	private enum MENU implements AbstractMenu {
		EXIT("exit");

		String menuClassName;
		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}
		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
		
	}
	
	@Override
	public AbstractMenu showUI() {
		System.out.println("rollcallui");
		Scanner scanner = new Scanner(System.in);
		
		scanner.close();
		return MENU.EXIT;
	}

}
