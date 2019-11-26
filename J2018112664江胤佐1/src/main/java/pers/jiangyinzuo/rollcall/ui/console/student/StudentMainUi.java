package pers.jiangyinzuo.rollcall.ui.console.student;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.RollCall;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import pers.jiangyinzuo.rollcall.ui.state.UserInfo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
     * 运行UI的方法
     *
     * @return 要跳转的UI, 若为null则结束程序
     */
    @Override
    public Class<? extends AbstractUi> run() {
        System.out.println(UserInfo.getSingleton().getStudent().welcome());
        ConsoleIoHelper.printMenu(new String[]{"1. 查看点名记录", "2. 查看课表", "3. 查看教学班详情", "4. 退出"});
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
		System.out.println("选择教学班");
    	for (TeachingClass cls : classList) {
			System.out.println(i++ + ". " + cls.getSchedule());
		}
    	int item = ConsoleIoHelper.scanItem(1, classList.size());
		try {
			RollCallService rollCallService = new RollCallServiceImpl(classList.get(item - 1));
			List<RollCall> resultList = rollCallService.queryRollCallsByStudentId(student.getStudentId());
			if (resultList.size() == 0) {
				System.out.println("暂无点名记录");
			}
			for (RollCall rollCall : resultList) {
                System.out.println(rollCall.getRollCallInfo());
			}
		} catch (ClassNotFoundException | CustomException | IOException | InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }

    /**
     * 查看课表
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
