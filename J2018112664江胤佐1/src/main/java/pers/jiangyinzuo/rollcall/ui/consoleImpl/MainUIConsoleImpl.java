package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.service.LoginService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.LoginServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.teacher.TeacherMainUI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student.StudentMainUI;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class MainUIConsoleImpl extends UI {
	public static enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT), STUDENT_MAIN_MENU(StudentMainUI.class.getName()),
		TEACHER_MAIN_MENU(TeacherMainUI.class.getName());

		private String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		public String getMenuClassName() {
			return this.menuClassName;
		}
	}

	public AbstractMenu showUI() throws CustomException, FileNotFoundException, IOException {
		String isStudent = "";
		String id = "";
		String pwd = "";
		LoginService loginService = new LoginServiceImpl();
		while (true) {
			System.out.println("-------------------------------------");
			System.out.println("欢迎, 请输入账号和密码, 或输入#退出程序");
			System.out.println("-------------------------------------");
			try {
				System.out.println("身份(1代表学生, 0代表教师):");

				isStudent = AppFile.scanner.nextLine();
				if (isStudent.equals("#")) {
					return MENU.EXIT;
				}
				if (!(isStudent.equals("1") || isStudent.equals("0"))) {
					System.out.println("请输入0或1");
					continue;
				}
				System.out.println("输入账号:");
				id = AppFile.scanner.nextLine();
				if (id.equals("#")) {
					break;
				}

				System.out.println("输入密码:");
				pwd = AppFile.scanner.nextLine();

				if (pwd.equals("#")) {
					break;
				}
				UserInfo userInfo = UserInfo.getSingleton();
				if (isStudent.equals("1")) {
					Student student = loginService.studentLogin(Integer.parseInt(id), pwd);
					if (student != null) {
						userInfo.setStudent(student);
						return MENU.STUDENT_MAIN_MENU;
					}
				} else {
					Teacher teacher = loginService.teacherLogin(Integer.parseInt(id), pwd);
					if (teacher != null) {

						userInfo.setTeacher(teacher);
						return MENU.TEACHER_MAIN_MENU;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("输入格式错误");
			} catch (StreamCorruptedException e) {
				System.out.println("账号不存在");
			} catch (CustomException e) {
				System.out.println(e.getErrInfo());
			} catch (Exception e) {
				System.out.println("未知错误");
				e.printStackTrace();
			}
		}
		return MENU.EXIT;
	}

	@Override
	protected void setSelectedMenuMap() {
		
	}
}
