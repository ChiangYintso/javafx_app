package main.java.pers.jiangyinzuo.rollcall.ui;

import java.io.IOException;

public interface UI {
	static enum UI_IMPLEMENT {
		CONSOLE, JAVA_FX;
	}
	static enum MENU {
		EXIT("exit"),
		STUDENT_MAIN_MENU("main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student.StudentMainMenuUI"),
		MAIN("main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.MainUIConsoleImpl"),
		SCHEDULE("main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.ScheduleUI");
		
		private String menuClassName;
		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}
	/**
	 * 
	 * @return UI名称, EXIT 表示退出
	 * @throws IOException 
	 */
	MENU showUI();
}
