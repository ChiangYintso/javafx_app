package main.java.pers.jiangyinzuo.rollcall.entity;

import java.util.List;

public class Schedule {
	enum WEEKDAY {
		MONDAY("周一"), TUESDAY("周二"), WEDNESDAY("周三"), THURSDAY("周四"), FRIDAY("周五"), SATURDAY("周六"), SUNDAY("周日"),
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
	private List<RollCall> rollCallList;

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

	public List<RollCall> getRollCallList() {
		return rollCallList;
	}

	public void setRollCallList(List<RollCall> rollCallList) {
		this.rollCallList = rollCallList;
	}
}
