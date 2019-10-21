package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
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
				System.out.println("�����ʽ����");
			} catch (CustomException e) {
				System.out.println(e.getErrInfo());
			} catch (Exception e) {
				System.out.println("δ֪����");
				e.printStackTrace();
			}
		}
		scanner.close();
		return MENU.EXIT;
	}
}
