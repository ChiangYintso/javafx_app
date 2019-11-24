package main.java.pers.jiangyinzuo.rollcall.ui.console.student;

import main.java.pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.console.common.ScheduleUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

/**
 * @author Jiang Yinzuo
 */
public class StudentMainUi extends AbstractUi {

	/**
	 * ����UI�ķ���
	 *
	 * @return Ҫ��ת��UI, ��Ϊnull���������
	 */
	@Override
	public Class<AbstractUi> run() {
		System.out.println(UserInfo.getSingleton().getStudent().welcome());
		System.out.println("1. �鿴������¼");
		System.out.println("2. �鿴�α�");
		System.out.println("3. �鿴��ѧ��");
		System.out.println("4. �˳�");
		return null;
	}
}
