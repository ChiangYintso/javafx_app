package test.java.pers.jiangyinzuo.rollcall.context;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.Schedule;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

public class Entity {
	public static Teacher[] teacherList = {
			new Teacher(123, "张三", "信息科学与技术学院", "男", "123456", "讲师", new ArrayList<>(), new ArrayList<>()) };

	public static Student[] studentList = { new Student(124, "女", "Alice", "软件2018-01班", "123456", "软件工程"),
			new Student(123, "男", "jyz", "软件2018-01班", "123456", "软件工程") };

	public static Schedule[] scheduleList = {
			new Schedule(1, "X7507", "3-17周", Schedule.WEEKDAY.FRIDAY, (short) 3, teacherList[0], null) };

	public static TeachingClass[] teachingClassList = { new TeachingClass(Integer.valueOf(1), "数学分析", 201901, 6666, (short) 2,
			"", 123, Arrays.asList(scheduleList), Arrays.asList(studentList)),
			new TeachingClass(Integer.valueOf(2), "大学物理B1", 201901, 6633, (short) 2,
					"", 123, Arrays.asList(scheduleList), Arrays.asList(studentList))};

	public static RollCall[] rollCallList = {
			new RollCall(1, "到场", "点名", Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)), teachingClassList[0], studentList[0]),
			new RollCall(2, "缺席", "点名", Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)), teachingClassList[0], studentList[0])};
			
	public static Schedule[] scheduleList = {
			new Schedule();
	}
	
	{
		scheduleList[0].setTeachingClass(teachingClassList[0]);
	}
}
