package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common.ScheduleUI;
import main.java.pers.jiangyinzuo.rollcall.util.Select;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common.RollCallUI;

public class StudentMainUI extends UI {
	private static enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT), SCHEDULE(ScheduleUI.class.getName()), TEACHING_CLASS(TeachingClassUI.class.getName()),
		ROLL_CALL(RollCallUI.class.getName());

		private String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}

	public StudentMainUI() {
		this.selectedMenuMap.put("1", MENU.ROLL_CALL);
		this.selectedMenuMap.put("2", MENU.SCHEDULE);
		this.selectedMenuMap.put("3", MENU.EXIT);
	}
	
	private void showStudentInfo() {
		
	}

	public AbstractMenu showUI() {
		return Select.selectMenu(selectedMenuMap, new String[] { "1. 查看课表", "2. 查看点名记录", "3. 退出" });
	}
}
