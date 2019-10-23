package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

public class TeachingClassUI extends UI {

	private List<TeachingClass> teachingClassList;
	private TeachingClassService service;

	public TeachingClassUI() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		this.service = new TeachingClassServiceImpl();
		this.userInfo = UserInfo.getSingleton();
		this.teachingClassList = this.service.queryTeachingClassesByStudentId(userInfo.getStudent().getStudentId());
		setSelectedMenuMap();
	}

	private enum MENU implements AbstractMenu {
		STUDENT_MAIN_MENU(StudentMainUI.class.getName()), EXIT(AbstractMenu.EXIT);

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
		showTeachingClassList();

		return Select.selectMenu(selectedMenuMap, new String[] { "1. ����", "2. �˳�" });
	}

	private void showTeachingClassList() {
		if (this.teachingClassList == null || this.teachingClassList.size() == 0) {
			System.out.println("��δѡ��!");
		}
		System.out.println("��ѡ��Ľ�ѧ�����£�");
		for (TeachingClass cls : this.teachingClassList) {
			cls.showTeachingClassInfo();
		}
	}

	@Override
	protected void setSelectedMenuMap() {
		this.selectedMenuMap.put("1", MENU.STUDENT_MAIN_MENU);
		this.selectedMenuMap.put("2", MENU.EXIT);

	}
}
