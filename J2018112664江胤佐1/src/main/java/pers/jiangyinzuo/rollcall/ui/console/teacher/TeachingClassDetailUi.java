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
//		System.out.println("��ǰѡ�еĽ�ѧ��:");
//		this.selectedTeachingClass.getCls().getTeachingClassInfo();
//		System.out.println("**********************************");
//
//		return this.operate();
//
//	}

//	private AbstractMenu operate() {
//		int item;
//		Select.printMenu(new String[] { "1. ����", "2. �鿴��ѧ����", "3. ����������" });
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
//			Select.printMenu(new String[] { "1. ����", "2. �鿴��ѧ����", "3. ����������" });
//		}
//		return MENU.TEACHER_MAIN_MENU;
//	}

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
		return null;
	}
}
