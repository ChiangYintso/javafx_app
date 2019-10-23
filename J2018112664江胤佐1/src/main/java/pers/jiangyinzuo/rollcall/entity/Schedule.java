package main.java.pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;

public class Schedule implements Serializable {
	public enum WEEKDAY {
		MONDAY("��һ"), TUESDAY("�ܶ�"), WEDNESDAY("����"), THURSDAY("����"), FRIDAY("����"), SATURDAY("����"), SUNDAY("����"),
		DEFAULT("");

		private final String weekday;

		WEEKDAY(String weekday) {
			this.weekday = weekday;
		}

		public String value() {
			return this.weekday;
		}
	}

	private Integer scheduleId;
	private String classRoom;
	private String weeks;
	private WEEKDAY weekday;
	private Short session;
	private Teacher teacher;
	private TeachingClass teachingClass;

	public Schedule(Integer scheduleId, String classRoom, String weeks, WEEKDAY weekday, Short session, Teacher teacher,
			TeachingClass teachingClass) {
		this.scheduleId = scheduleId;
		this.classRoom = classRoom;
		this.weeks = weeks;
		this.weekday = weekday;
		this.session = session;
		this.teacher = teacher;
		this.teachingClass = teachingClass;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public WEEKDAY getWeekday() {
		return weekday;
	}

	public void setWeekday(WEEKDAY weekday) {
		this.weekday = weekday;
	}

	public Short getSession() {
		return session;
	}

	public void setSession(Short session) {
		this.session = session;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeachingClass getTeachingClass() {
		return teachingClass;
	}

	public void setTeachingClass(TeachingClass teachingClass) {
		this.teachingClass = teachingClass;
	}
}
