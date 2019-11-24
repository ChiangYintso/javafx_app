package main.java.pers.jiangyinzuo.rollcall.ui.console.teacher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;
import main.java.pers.jiangyinzuo.rollcall.helper.FileHelper;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

public class TeachingClassDetailUi extends AbstractUi {

	private SelectedTeachingClass selectedTeachingClass;
	private List<Student> studentList;

	private static enum MENU implements AbstractMenu {
		ROLL_CALL(RollCallUi.class.getName()),
		TEACHER_MAIN_MENU(TeacherMainUi.class.getName());

		private String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}

	public TeachingClassDetailUi() {
		selectedTeachingClass = SelectedTeachingClass.getSingleton();
		studentList = this.selectedTeachingClass.getCls().getStudentList();
	}

	@Override
	public AbstractMenu showUi() throws CustomException, FileNotFoundException, IOException {
		System.out.println("**********************************");
		System.out.println("��ǰѡ�еĽ�ѧ��:");
		this.selectedTeachingClass.getCls().getTeachingClassInfo();
		System.out.println("**********************************");

		return this.operate();
		
	}

	private AbstractMenu operate() {
		int item;
		Select.printMenu(new String[] { "1. ����", "2. �鿴��ѧ����", "3. ����������" });
		while ((item = FileHelper.scanItem(1, 3)) != 3) {
			switch (item) {
			case 1:
				return MENU.ROLL_CALL;
			case 2:
				this.showTeachingList();
				break;
			case 3:
				return MENU.TEACHER_MAIN_MENU;
			default:
				break;
			}
			Select.printMenu(new String[] { "1. ����", "2. �鿴��ѧ����", "3. ����������" });
		}
		return MENU.TEACHER_MAIN_MENU;
	}

	private void showTeachingList() {
		if (studentList == null || studentList.size() == 0) {
			System.out.println("��0��ѧ��");
		} else {
			for (Student s : studentList) {
				System.out.println(s.getStudentName() + " " + s.getStudentId());
			}
			System.out.printf("��%d��ѧ��\n", studentList.size());
		}
	}

	@Override
	protected void setSelectedMenuMap() {
		// TODO Auto-generated method stub
		
	};
}
