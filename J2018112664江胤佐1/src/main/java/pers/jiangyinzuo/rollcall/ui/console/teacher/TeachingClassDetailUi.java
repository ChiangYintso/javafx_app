package main.java.pers.jiangyinzuo.rollcall.ui.console.teacher;

import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;

public class TeachingClassDetailUi extends AbstractUi {

	private SelectedTeachingClass selectedTeachingClass;
	private List<Student> studentList;

	public TeachingClassDetailUi() {
		selectedTeachingClass = SelectedTeachingClass.getSingleton();
		studentList = this.selectedTeachingClass.getCls().getStudentList();
	}

//	@Override
//	public AbstractMenu showUi() throws CustomException, FileNotFoundException, IOException {
//		System.out.println("**********************************");
//		System.out.println("当前选中的教学班:");
//		this.selectedTeachingClass.getCls().getTeachingClassInfo();
//		System.out.println("**********************************");
//
//		return this.operate();
//
//	}

//	private AbstractMenu operate() {
//		int item;
//		Select.printMenu(new String[] { "1. 点名", "2. 查看教学名单", "3. 返回主界面" });
//		while ((item = FileHelper.scanItem(1, 3)) != 3) {
//			switch (item) {
//			case 1:
//				return MENU.ROLL_CALL;
//			case 2:
//				this.showTeachingList();
//				break;
//			case 3:
//				return MENU.TEACHER_MAIN_MENU;
//			default:
//				break;
//			}
//			Select.printMenu(new String[] { "1. 点名", "2. 查看教学名单", "3. 返回主界面" });
//		}
//		return MENU.TEACHER_MAIN_MENU;
//	}

	private void showTeachingList() {
		if (studentList == null || studentList.size() == 0) {
			System.out.println("共0个学生");
		} else {
			for (Student s : studentList) {
				System.out.println(s.getStudentName() + " " + s.getStudentId());
			}
			System.out.printf("共%d个学生\n", studentList.size());
		}
	}


	/**
	 * 运行UI的方法
	 *
	 * @return 要跳转的UI, 若为null则结束程序
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		return null;
	}
}
