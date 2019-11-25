package main.java.pers.jiangyinzuo.rollcall.ui.console.student;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import main.java.pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.service.RollCallService;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

import java.io.IOException;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class StudentMainUi extends AbstractUi {

    TeachingClassService teachingClassService;
    Student student;
    List<TeachingClass> classList;

    public StudentMainUi() {
        teachingClassService = new TeachingClassServiceImpl();
        student = UserInfo.getSingleton().getStudent();
        try {
            classList = teachingClassService.queryTeachingClassesByStudentId(student.getStudentId());
        } catch (ClassNotFoundException | IOException | CustomException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����UI�ķ���
     *
     * @return Ҫ��ת��UI, ��Ϊnull���������
     */
    @Override
    public Class<? extends AbstractUi> run() {
        System.out.println(UserInfo.getSingleton().getStudent().welcome());
        ConsoleIoHelper.printMenu(new String[]{"1. �鿴������¼", "2. �鿴�α�", "3. �鿴��ѧ������", "4. �˳�"});
        int item = ConsoleIoHelper.scanItem(1, 4);
        switch (item) {
            case 1:
                showRollCallRecord();
                return this.getClass();
            case 2:
                showSchedule();
                return this.getClass();
            case 3:
                showTeachingClassDetail();
                return this.getClass();
            default:
                return null;
        }
    }

    private void showRollCallRecord() {
    	int i = 1;
		System.out.println("ѡ���ѧ��");
    	for (TeachingClass cls : classList) {
			System.out.println(i++ + ". " + cls.getSchedule());
		}
    	int item = ConsoleIoHelper.scanItem(1, classList.size());
		try {
			RollCallService rollCallService = new RollCallServiceImpl(classList.get(item - 1));
			List<RollCall> resultList = rollCallService.queryRollCallsByStudentId(student.getStudentId());
			if (resultList.size() == 0) {
				System.out.println("���޵�����¼");
			}
			for (RollCall rollCall : resultList) {
                System.out.println(rollCall.getRollCallInfo());
			}
		} catch (ClassNotFoundException | CustomException | IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * �鿴�α�
     */
    private void showSchedule() {
        for (TeachingClass cls : classList) {
            System.out.println(cls.getSchedule());
        }
    }

    private void showTeachingClassDetail() {
        for (TeachingClass cls : classList) {
            System.out.println(cls.getTeachingClassInfo());
        }
    }
}
