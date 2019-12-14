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
