package main.java.pers.jiangyinzuo.rollcall.ui.console.student;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.console.common.ScheduleUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

/**
 * @author Jiang Yinzuo
 */
public class StudentMainUi extends AbstractUi {
	private enum MENU implements AbstractMenu {
		// TODO ROLL_CALL
		EXIT(AbstractMenu.EXIT), SCHEDULE(ScheduleUi.class.getName()), TEACHING_CLASS(TeachingClassUi.class.getName()),
		ROLL_CALL(AbstractMenu.EXIT);

		private String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}

	public StudentMainUi() {
		setSelectedMenuMap();
	}

	@Override
	public AbstractMenu showUi() {
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
