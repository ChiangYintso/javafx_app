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
			new Teacher(123, "����", "��Ϣ��ѧ�뼼��ѧԺ", "��", "123456", "��ʦ", new ArrayList<>(), new ArrayList<>()) };

	public static Student[] studentList = { new Student(124, "Ů", "Alice", "���2018-01��", "123456", "�������"),
			new Student(123, "��", "jyz", "���2018-01��", "123456", "�������") };

	public static Schedule[] scheduleList = {
			new Schedule(1, "X7507", "3-17��", Schedule.WEEKDAY.FRIDAY, (short) 3, teacherList[0], null) };

	public static TeachingClass[] teachingClassList = { new TeachingClass(Integer.valueOf(1), "��ѧ����", 201901, 6666, (short) 2,
			"", 123, Arrays.asList(scheduleList), Arrays.asList(studentList)),
			new TeachingClass(Integer.valueOf(2), "��ѧ����B1", 201901, 6633, (short) 2,
					"", 123, Arrays.asList(scheduleList), Arrays.asList(studentList))};

	public static RollCall[] rollCallList = {
			new RollCall(1, "����", "����", Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)), teachingClassList[0], studentList[0]),
			new RollCall(2, "ȱϯ", "����", Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)), teachingClassList[0], studentList[0])};
			
	public static Schedule[] scheduleList = {
			new Schedule();
	}
	
	{
		scheduleList[0].setTeachingClass(teachingClassList[0]);
	}
}
