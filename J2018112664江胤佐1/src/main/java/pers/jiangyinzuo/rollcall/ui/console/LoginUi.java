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
	 * ����UI�ķ���
	 *
	 * @return Ҫ��ת��UI, ��Ϊnull���������
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		String isStudent = "";
		String id = "";
		String pwd = "";
		LoginService loginService = new LoginServiceImpl();
		while (true) {
			System.out.println("-------------------------------------");
			System.out.println("��ӭ, �������˺ź�����, ������#�˳�����");
			System.out.println("(ѧ������ʦ�����˺�123����123456)");
			System.out.println("-------------------------------------");
			try {
				System.out.println("���(1����ѧ��, 0�����ʦ):");

				isStudent = ConsoleIoHelper.scanner.nextLine();
				if (isStudent.equals("#")) {
					return null;
				}
				if (!(isStudent.equals("1") || isStudent.equals("0"))) {
					System.out.println("������0��1");
					continue;
				}
				System.out.println("�����˺�:");
				id = ConsoleIoHelper.scanner.nextLine();
				if (id.equals("#")) {
					break;
				}

				System.out.println("��������:");
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
				System.out.println("�����ʽ����");
			} catch (StreamCorruptedException e) {
				System.out.println("�˺Ų�����");
			} catch (CustomException e) {
				System.out.println(e.getErrInfo());
			} catch (ClassNotFoundException e) {
				System.out.println("�˺Ż��������");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
