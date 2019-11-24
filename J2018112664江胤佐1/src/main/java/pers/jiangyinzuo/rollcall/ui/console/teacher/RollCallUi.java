package main.java.pers.jiangyinzuo.rollcall.ui.console.teacher;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import main.java.pers.jiangyinzuo.rollcall.service.RollCallService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.RollCallServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;
import main.java.pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class RollCallUi extends AbstractUi {
	
	private RollCallService service;
	private List<RollCall> rollCallList;
	private TeachingClass selectedTeachingClass;
	private Map<Integer, String> presenceMap;
	
	public RollCallUi() throws ClassNotFoundException, CustomException, IOException {
		this.selectedTeachingClass = SelectedTeachingClass.getSingleton().getCls();
		if (this.selectedTeachingClass == null) {
			System.out.println("未选择班级");
			return;
		}
		this.service = new RollCallServiceImpl(this.selectedTeachingClass);
		this.rollCallList = this.service.queryTeachingClassRollCalls();
		this.presenceMap = new HashMap<>();
		this.presenceMap.put(1, "已到");
		this.presenceMap.put(2, "未到");
		this.presenceMap.put(3, "迟到");
		this.presenceMap.put(4, "请假");
		this.presenceMap.put(5, "早退");
	}

	private void addRollCallRecord(List<Student> studentList, String rollCallType) {
		if (studentList == null || studentList.size() == 0) {
			System.out.println("无点名记录");
			return;
		}
		System.out.println("1. 已到；2. 未到； 3. 迟到； 4. 请假； 5. 早退");
		int item;
		
		if (studentList != null) {
			for (Student student : studentList) {
				System.out.println(student.getStudentName() + " " + student.getStudentId());
				item = ConsoleIoHelper.scanItem(1, 5);
				try {
					service.insertRollCall(student, presenceMap.get(Integer.valueOf(item)), rollCallType);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void editRollCallRecord() throws IOException {
		if (this.rollCallList == null || this.rollCallList.size() == 0) {
			System.out.println("无点名记录");
		} else {
			int idx = 1;
			for (RollCall rollCall : this.rollCallList) {
				System.out.printf("%d: |", idx++);
				System.out.println(rollCall.getRollCallInfo());
			}
		}
		System.out.println("输入需要修改的点名记录");
		int item = ConsoleIoHelper.scanItem(1, this.rollCallList.size());
		System.out.println("1. 已到；2. 未到； 3. 迟到； 4. 请假； 5. 早退");
		RollCall rollCall = this.rollCallList.get(item-1);
		RollCall tempRollCall = rollCall.copy();
		item = ConsoleIoHelper.scanItem(1, this.rollCallList.size());
		rollCall.setPresence(presenceMap.get(Integer.valueOf(item)));
		service.editRollCall(tempRollCall, rollCall);
	}

	/**
	 * 运行UI的方法
	 *
	 * @return 要跳转的UI, 若为null则结束程序
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		int item = ConsoleIoHelper.selectMenuItem((new String[] { "1. 异常点名", "2. 全体点名", "3. 随机点名", "4. 修改点名记录","5. 提问",  "6. 返回主菜单" }));
		int count;
		switch (item) {
			case 1:
				addRollCallRecord(service.getAbnormalStudent(), "点名");
				return this.getClass();
			case 2:
				addRollCallRecord(this.selectedTeachingClass.getStudentList(), "点名");
				return this.getClass();
			case 3:
				System.out.println("请输入数字1-"+ this.selectedTeachingClass.getStudentList().size());
				count = ConsoleIoHelper.scanItem(1, this.selectedTeachingClass.getStudentList().size());
				addRollCallRecord(this.service.getRandomStudent(count), "点名");
				return this.getClass();
			case 4:
				try {
					editRollCallRecord();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return this.getClass();
			case 5:
				System.out.println("请输入数字1-"+ this.selectedTeachingClass.getStudentList().size());
				count = ConsoleIoHelper.scanItem(1, this.selectedTeachingClass.getStudentList().size());
				addRollCallRecord(this.service.getRandomStudent(count), "提问");
				return this.getClass();
			case 6:
				return TeacherMainUi.class;
			default:
				return null;
		}
	}
}
