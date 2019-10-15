package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student;

import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;

public class TeachingClassUI extends UI {
	private enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT);
		
		private String menuClassName;
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
