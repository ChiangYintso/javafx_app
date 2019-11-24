package main.java.pers.jiangyinzuo.rollcall.ui.console.teacher;

import main.java.pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.console.common.ScheduleUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

/**
 * @author Jiang Yinzuo
 */
public class TeacherMainUi extends AbstractUi {

	/**
	 * ����UI�ķ���
	 *
	 * @return Ҫ��ת��UI, ��Ϊnull���������
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		UserInfo userInfo = UserInfo.getSingleton();
		System.out.println(userInfo.getTeacher().welcome());
		System.out.println("1. �鿴�α�\n2. ѡ���ѧ��\n3. �˳�");
		return null;
	}
}
