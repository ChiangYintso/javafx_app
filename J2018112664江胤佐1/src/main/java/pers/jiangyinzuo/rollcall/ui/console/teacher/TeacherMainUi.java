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
	 * 运行UI的方法
	 *
	 * @return 要跳转的UI, 若为null则结束程序
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		UserInfo userInfo = UserInfo.getSingleton();
		System.out.println(userInfo.getTeacher().welcome());
		System.out.println("1. 查看课表\n2. 选择教学班\n3. 退出");
		return null;
	}
}
