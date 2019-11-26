package pers.jiangyinzuo.rollcall.ui.console;

import java.io.IOException;
import java.io.StreamCorruptedException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.Teacher;
import pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import pers.jiangyinzuo.rollcall.service.LoginService;
import pers.jiangyinzuo.rollcall.service.impl.LoginServiceImpl;
import pers.jiangyinzuo.rollcall.ui.console.teacher.TeacherMainUi;
import pers.jiangyinzuo.rollcall.ui.console.student.StudentMainUi;
import pers.jiangyinzuo.rollcall.ui.state.UserInfo;

/**
 * @author Jiang Yinzuo
 */
public class LoginUi extends AbstractUi {

	/**
	 * 运行UI的方法
	 *
	 * @return 要跳转的UI, 若为null则结束程序
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		String isStudent = "";
		String id = "";
		String pwd = "";
		LoginService loginService = new LoginServiceImpl();
		while (true) {
			System.out.println("-------------------------------------");
			System.out.println("欢迎, 请输入账号和密码, 或输入#退出程序");
			System.out.println("(学生和老师都是账号123密码123456)");
			System.out.println("-------------------------------------");
			try {
				System.out.println("身份(1代表学生, 0代表教师):");

				isStudent = ConsoleIoHelper.scanner.nextLine();
				if (isStudent.equals("#")) {
					return null;
				}
				if (!(isStudent.equals("1") || isStudent.equals("0"))) {
					System.out.println("请输入0或1");
					continue;
				}
				System.out.println("输入账号:");
				id = ConsoleIoHelper.scanner.nextLine();
				if (id.equals("#")) {
					break;
				}

				System.out.println("输入密码:");
				pwd = ConsoleIoHelper.scanner.nextLine();

				if (pwd.equals("#")) {
					break;
				}
				UserInfo userInfo = UserInfo.getSingleton();
				if (isStudent.equals("1")) {
					Student student = loginService.studentLogin(Long.parseLong(id), pwd);
					if (student != null) {
						userInfo.setStudent(student);
						return StudentMainUi.class;
					}
				} else {
					Teacher teacher = loginService.teacherLogin(Long.parseLong(id), pwd);
					if (teacher != null) {

						userInfo.setTeacher(teacher);
						return TeacherMainUi.class;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("输入格式错误");
			} catch (StreamCorruptedException e) {
				System.out.println("账号不存在");
			} catch (CustomException e) {
				System.out.println(e.getErrInfo());
			} catch (ClassNotFoundException e) {
				System.out.println("账号或密码错误");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
