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
			System.out.println("��ӭ, �������˺ź�����, ������#�˳�����");
			System.out.println("-------------------------------------");
			try {
				System.out.println("���(1����ѧ��, 0�����ʦ):");

				isStudent = scanner.nextLine();
				if (isStudent.equals("#")) {
					scanner.close();
					return MENU.EXIT;
				}
				if (!(isStudent.equals("1") || isStudent.equals("0"))) {
					System.out.println("������0��1");
					continue;
				}
				System.out.println("�����˺�:");
				id = scanner.nextLine();
				if (id.equals("#")) {
					break;
				}

				System.out.println("��������:");
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
				System.out.println("�����ʽ����");
			}
		}
		scanner.close();
		return MENU.EXIT;
	}
}
