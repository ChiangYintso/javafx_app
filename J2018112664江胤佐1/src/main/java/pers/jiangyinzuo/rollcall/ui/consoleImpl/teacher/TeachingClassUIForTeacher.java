package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.teacher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class TeachingClassUIForTeacher extends UI {

	private UserInfo userInfo;
	private List<TeachingClass> teachingClassList;
	private TeachingClassService service;
	
	public TeachingClassUIForTeacher() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		// 获取用户信息单例
		userInfo = UserInfo.getSingleton();
		
		service = new TeachingClassServiceImpl();
		
		teachingClassList = service.queryTeachingClassesByTeacherId(userInfo.getTeacher().getTeacherId());
	}
	
	private static enum MENU implements AbstractMenu {
		TEACHING_CLASS_DETAIL(TeachingClassDetailUI.class.getName()),
		TEACHER_MAIN_MENU(TeacherMainUI.class.getName());
		
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
	public AbstractMenu showUI() throws CustomException, FileNotFoundException, IOException {
		return selectTeachingClass();
	}
	
	private AbstractMenu selectTeachingClass() {
		if (this.teachingClassList == null || this.teachingClassList.size() == 0) {
			System.out.println("*************");
			System.out.println("* 暂无教学班  *");
			System.out.println("*************");
		} else {
			System.out.println("请选择需要操作的教学班, 输入0返回");
			int order = 0;
			for (TeachingClass cls : this.teachingClassList) {
				System.out.printf("%d. | ", ++order);
				cls.showTeachingClassInfo();
			}
			
			String line = "";
			int selection = -1;
			while (selection != 0) {
				try {
					line = AppFile.scanner.nextLine();
					selection = Integer.parseInt(line);

					if (selection < 1 || selection > this.teachingClassList.size()) {
						System.out.printf("请输入数字1 - %d\n", this.teachingClassList.size());
					} else {
						SelectedTeachingClass selectedTeachingClass = SelectedTeachingClass.getSingleton();
						selectedTeachingClass.setCls(this.teachingClassList.get(selection-1));
						return MENU.TEACHING_CLASS_DETAIL;
					}
				} catch(NumberFormatException e) {
					System.out.println("输入格式有误");
					continue;
				}
			}
		}

		return MENU.TEACHER_MAIN_MENU;
	}

	@Override
	protected void setSelectedMenuMap() {
		selectedMenuMap.put("0", MENU.TEACHER_MAIN_MENU);
	}

}
