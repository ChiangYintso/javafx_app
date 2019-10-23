package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common.ScheduleUI;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.teacher.RollCallUI;

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
		setSelectedMenuMap();
		
	}

	public AbstractMenu showUI() {
		UserInfo userInfo = UserInfo.getSingleton();
		userInfo.getStudent().welcome();

		return Select.selectMenu(selectedMenuMap, new String[] { "1. 查看点名记录", "2. 查看课表", "3. 查看教学班", "4. 退出" });
	}

	@Override
	protected void setSelectedMenuMap() {
		this.selectedMenuMap.put("1", MENU.ROLL_CALL);
		this.selectedMenuMap.put("2", MENU.SCHEDULE);
		this.selectedMenuMap.put("3", MENU.TEACHING_CLASS);
		this.selectedMenuMap.put("4", MENU.EXIT);
	}
}
