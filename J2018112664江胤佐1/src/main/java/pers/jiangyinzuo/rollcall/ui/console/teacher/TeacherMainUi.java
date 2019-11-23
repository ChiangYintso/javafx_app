package main.java.pers.jiangyinzuo.rollcall.ui.console.teacher;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.Ui;
import main.java.pers.jiangyinzuo.rollcall.ui.console.common.ScheduleUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

public class TeacherMainUi extends Ui {

	private static enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT),
		SCHEDULE(ScheduleUi.class.getName()),
		TEACHING_CLASS(TeachingClassUiForTeacher.class.getName());

		private String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}
	
	public TeacherMainUi() {
		setSelectedMenuMap();
	}
	
	@Override
	public AbstractMenu showUi() {
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
