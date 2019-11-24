package main.java.pers.jiangyinzuo.rollcall.ui.console.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

public class TeachingClassUi extends AbstractUi {

	private List<TeachingClass> teachingClassList;
	private TeachingClassService service;

	public TeachingClassUi() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		this.service = new TeachingClassServiceImpl();
		this.userInfo = UserInfo.getSingleton();
		this.teachingClassList = this.service.queryTeachingClassesByStudentId(userInfo.getStudent().getStudentId());
		setSelectedMenuMap();
	}

	private enum MENU implements AbstractMenu {
		STUDENT_MAIN_MENU(StudentMainUi.class.getName()), EXIT(AbstractMenu.EXIT);

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
	public AbstractMenu showUi() {
		showTeachingClassList();

		return Select.selectMenu(selectedMenuMap, new String[] { "1. 返回", "2. 退出" });
	}

	private void showTeachingClassList() {
		if (this.teachingClassList == null || this.teachingClassList.size() == 0) {
			System.out.println("尚未选课!");
		}
		System.out.println("你选择的教学班如下：");
		for (TeachingClass cls : this.teachingClassList) {
			cls.getTeachingClassInfo();
		}
	}

	@Override
	protected void setSelectedMenuMap() {
		this.selectedMenuMap.put("1", MENU.STUDENT_MAIN_MENU);
		this.selectedMenuMap.put("2", MENU.EXIT);

	}
}
