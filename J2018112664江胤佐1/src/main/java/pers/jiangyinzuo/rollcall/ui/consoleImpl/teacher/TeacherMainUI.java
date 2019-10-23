package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.teacher;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common.ScheduleUI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student.TeachingClassUI;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

public class TeacherMainUI extends UI {

	private static enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT),
		SCHEDULE(ScheduleUI.class.getName()),
		TEACHING_CLASS(TeachingClassUIForTeacher.class.getName());

		private String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}
	
	public TeacherMainUI() {
		setSelectedMenuMap();
	}
	
	@Override
	public AbstractMenu showUI() {
		UserInfo userInfo = UserInfo.getSingleton();
		userInfo.getTeacher().welcome();
		return Select.selectMenu(selectedMenuMap, new String[] {"1. 查看课表", "2. 选择教学班", "3. 退出"});
	}

	@Override
	protected void setSelectedMenuMap() {
		selectedMenuMap.put("1", MENU.SCHEDULE);
		selectedMenuMap.put("2", MENU.TEACHING_CLASS);
		selectedMenuMap.put("3", MENU.EXIT);
	}

}
