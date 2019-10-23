package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.teacher;

import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.RollCallService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.RollCallServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

public class RollCallUI extends UI {
	
	private RollCallService service;
	private List<RollCall> rollCallList;
	private TeachingClass selectedTeachingClass;

	private enum MENU implements AbstractMenu {
		TEACHER_MAIN_MENU(TeacherMainUI.class.getName());

		String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}
	
	public RollCallUI() throws ClassNotFoundException, CustomException, IOException {
		setSelectedMenuMap();
		this.service = new RollCallServiceImpl();
		this.selectedTeachingClass = SelectedTeachingClass.getSingleton().getCls();
		this.rollCallList = this.service.queryRollCallsByTeachingClassId(selectedTeachingClass.getClassId());
	}

	@Override
	public AbstractMenu showUI() {
		operate();

		return MENU.TEACHER_MAIN_MENU;
	}

	@Override
	protected void setSelectedMenuMap() {
		this.selectedMenuMap.put("1", MENU.TEACHER_MAIN_MENU);
	}

	private void operate() {
		int option; 
		while (true) {
			Select.printMenu(new String[] { "1. 异常点名", "2. 全体点名", "3. 随机点名", "4. 修改点名记录", "5. 返回主菜单" });
			option = AppFile.scanItem(1, 5);
			switch (option) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				editRollCallRecord();
				break;
			case 4:
				break;
			case 5:
				return;
			default:
				break;
			}
		}
	}
	
	private void editRollCallRecord() {
		if (this.rollCallList == null || this.rollCallList.size() == 0) {
			System.out.println("无点名记录");
		} else {
			int idx = 1;
			for (RollCall rollCall : this.rollCallList) {
				System.out.printf("%d: |", idx++);
				rollCall.showRollCallRecord();
			}
		}
		System.out.println("输入需要修改的点名记录");
	}
}
