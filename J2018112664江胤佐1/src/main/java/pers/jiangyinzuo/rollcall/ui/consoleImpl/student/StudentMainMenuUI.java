package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common.ScheduleUI;

public class StudentMainMenuUI implements UI {
	private static enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT),
		SCHEDULE(ScheduleUI.class.getName()),
		TEACHING_CLASS(TeachingClassUI.class.getName());
		
		private String menuClassName;
		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}
		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}
	
	private Map<String, MENU> selectMenuMap = new HashMap<>();
	
	public StudentMainMenuUI() {
		this.selectMenuMap.put("1", MENU.EXIT);
		this.selectMenuMap.put("2", MENU.SCHEDULE);
	}
	
	public AbstractMenu showUI() {
		Scanner scanner = new Scanner(System.in);
		int option;
		while (true) {
			try {
				System.out.println("---------------");
				System.out.println("1. 查看课表");
				System.out.println("2. 查看教学班");
				System.out.println("3. 查看点名记录");
				System.out.println("4. 退出");
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
