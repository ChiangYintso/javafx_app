package pers.jiangyinzuo.rollcall.ui.console.teacher;

import java.util.List;

import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

public class TeachingClassDetailUi extends AbstractUi {

	private SelectedTeachingClassState selectedTeachingClass;
	private List<Student> studentList;

	public TeachingClassDetailUi() {
		selectedTeachingClass = SelectedTeachingClassState.getSingleton();
//		studentList = this.selectedTeachingClass.getCls().getStudentList();
	}

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
		System.out.println("**********************************");
		System.out.println("当前选中的教学班:");
		System.out.println(this.selectedTeachingClass.getCls().getTeachingClassInfo());
		System.out.println("**********************************");
		int item = ConsoleIoHelper.selectMenuItem(new String[] {"1. 点名", "2. 查看教学名单", "3. 返回主界面"});
		switch (item) {
			case 1:
				return RollCallUi.class;
			case 2:
				this.showTeachingList();
				return TeacherMainUi.class;
			default:
				return TeacherMainUi.class;
		}
	}
}
