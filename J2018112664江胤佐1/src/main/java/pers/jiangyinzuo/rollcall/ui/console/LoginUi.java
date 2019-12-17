package pers.jiangyinzuo.rollcall.ui.console;

import java.lang.reflect.InvocationTargetException;

import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;
import pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import pers.jiangyinzuo.rollcall.service.LoginService;
import pers.jiangyinzuo.rollcall.service.impl.LoginServiceImpl;
import pers.jiangyinzuo.rollcall.ui.console.teacher.TeacherMainUi;
import pers.jiangyinzuo.rollcall.ui.console.student.StudentMainUi;
import pers.jiangyinzuo.rollcall.ui.state.UserState;

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
    public Class<? extends AbstractUi> run() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String isStudent = "";
        String id = "";
        String pwd = "";
        LoginService loginService = new LoginServiceImpl();
        while (true) {
            System.out.println("-------------------------------------");
            System.out.println("��ӭ, �������˺ź�����, ������#�˳�����");
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
                UserState userInfo = UserState.getSingleton();
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
            }
        }
        return null;
    }
}
