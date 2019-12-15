package pers.jiangyinzuo.rollcall.ui.console.teacher;


import pers.jiangyinzuo.rollcall.domain.entity.Teacher;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;
import pers.jiangyinzuo.rollcall.ui.state.UserState;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class TeacherMainUi extends AbstractUi {

	List<TeachingClass> classList;
	TeachingClassService teachingClassService;
	Teacher teacher;

	public TeacherMainUi() {
		teachingClassService = new TeachingClassServiceImpl();
		teacher = UserState.getSingleton().getTeacher();
		classList = teachingClassService.queryTeachingClassesByTeacherId(teacher.getTeacherId());
	}

	/**
	 * ����UI�ķ���
	 *
	 * @return Ҫ��ת��UI, ��Ϊnull���������
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		UserState userInfo = UserState.getSingleton();
		System.out.println(userInfo.getTeacher().welcome());
		int item = ConsoleIoHelper.selectMenuItem(new String[] {"1. �鿴�α�", "2. ѡ���ѧ��", "3. �˳�"});
		switch (item) {
			case 1:
				showSchedule();
				return this.getClass();
			case 2:
				chooseTeachingClass();
				return TeachingClassDetailUi.class;
			default:
				return null;
		}
	}

	private void showSchedule() {
		for (TeachingClass cls : classList) {
			System.out.println(cls.getSchedule());
		}
	}

	private void chooseTeachingClass() {
		System.out.println("��ѡ���ѧ��: ");
		int i = 1;
		for (TeachingClass cls : classList) {
			System.out.println(i++ + ". " + cls.getTeachingClassInfo());
		}
		int item = ConsoleIoHelper.scanItem(1, classList.size());
		SelectedTeachingClassState.getSingleton().setCls(classList.get(item - 1));
	}
}
