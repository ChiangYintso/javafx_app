package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl;

import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.service.LoginService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.LoginServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.student.StudentMainMenuUI;

public class MainUIConsoleImpl implements UI {
	public static enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT), STUDENT_MAIN_MENU(StudentMainMenuUI.class.getName());

		private String menuClassName;

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

		public String getMenuClassName() {
			return this.menuClassName;
		}
	}

	public AbstractMenu showUI() {
		String isStudent = "";
		String id = "";
		String pwd = "";
		LoginService loginService = new LoginServiceImpl();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("-------------------------------------");
			System.out.println("欢迎, 请输入账号和密码, 或输入#退出程序");
			System.out.println("-------------------------------------");
			try {
				System.out.println("身份(1代表学生, 0代表教师):");

				isStudent = scanner.nextLine();
				if (isStudent.equals("#")) {
					scanner.close();
					return MENU.EXIT;
				}
				if (!(isStudent.equals("1") || isStudent.equals("0"))) {
					System.out.println("请输入0或1");
					continue;
				}
				System.out.println("输入账号:");
				id = scanner.nextLine();
				if (id.equals("#")) {
					break;
				}

				System.out.println("输入密码:");
				pwd = scanner.nextLine();

				if (pwd.equals("#")) {
					break;
				}
				if (isStudent.equals("1")) {
					if (loginService.validateStudent(Integer.parseInt(id), pwd)) {
						return MENU.STUDENT_MAIN_MENU;
					}
				} else {
					if (loginService.validateTeacher(Integer.parseInt(id), pwd)) {
						return MENU.STUDENT_MAIN_MENU;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("输入格式错误");
			}
		}
		scanner.close();
		return MENU.EXIT;
	}
}
