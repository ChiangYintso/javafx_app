package main.java.pers.jiangyinzuo.rollHelper.entity;

import java.time.Instant;
import java.util.List;

public class RollCall {
	private Integer rollCallId;
	private String presence;
	private String rollCallType;
	private Instant rollCallTime;
	private Schedule schedule;
	private TeachingClass teachingClass;
	private List<Student> studentList;

	public Integer getRollCallId() {
		return rollCallId;
	}

	public void setRollCallId(Integer rollCallId) {
		this.rollCallId = rollCallId;
	}

	public String getPresence() {
		return presence;
	}

	public void setPresence(String presence) {
		this.presence = presence;
	}

	public String getRollCallType() {
		return rollCallType;
	}

	public void setRollCallType(String rollCallType) {
		this.rollCallType = rollCallType;
	}

	public Instant getRollCallTime() {
		return rollCallTime;
	}

	public void setRollCallTime(Instant rollCallTime) {
		this.rollCallTime = rollCallTime;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public TeachingClass getTeachingClass() {
		return teachingClass;
	}

	public void setTeachingClass(TeachingClass teachingClass) {
		this.teachingClass = teachingClass;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
}
