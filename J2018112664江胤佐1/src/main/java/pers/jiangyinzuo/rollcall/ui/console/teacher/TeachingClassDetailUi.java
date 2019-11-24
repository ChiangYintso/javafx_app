package main.java.pers.jiangyinzuo.rollcall.ui.console.teacher;

import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import main.java.pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;

public class TeachingClassDetailUi extends AbstractUi {

	private SelectedTeachingClass selectedTeachingClass;
	private List<Student> studentList;

	public TeachingClassDetailUi() {
		selectedTeachingClass = SelectedTeachingClass.getSingleton();
		studentList = this.selectedTeachingClass.getCls().getStudentList();
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

	/**
	 * ����UI�ķ���
	 *
	 * @return Ҫ��ת��UI, ��Ϊnull���������
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		System.out.println("**********************************");
		System.out.println("��ǰѡ�еĽ�ѧ��:");
		System.out.println(this.selectedTeachingClass.getCls().getTeachingClassInfo());
		System.out.println("**********************************");
		int item = ConsoleIoHelper.selectMenuItem(new String[] {"1. ����", "2. �鿴��ѧ����", "3. ����������"});
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
