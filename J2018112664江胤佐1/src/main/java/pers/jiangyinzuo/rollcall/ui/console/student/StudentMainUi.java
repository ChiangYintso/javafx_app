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
	 * 运行UI的方法
	 *
	 * @return 要跳转的UI, 若为null则结束程序
	 */
	@Override
	public Class<AbstractUi> run() {
		System.out.println(UserInfo.getSingleton().getStudent().welcome());
		System.out.println("1. 查看点名记录");
		System.out.println("2. 查看课表");
		System.out.println("3. 查看教学班");
		System.out.println("4. 退出");
		return null;
	}
}
