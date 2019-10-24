package main.java.pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.time.Instant;

public class RollCall implements Serializable {
	private Integer rollCallId;

	/* 已到、未到、迟到、请假、早退 */
	private String presence;
	private String rollCallType;
	private Instant rollCallTime;
	private TeachingClass teachingClass;
	private Student student;

	public RollCall(Integer rollCallId, String presence, String rollCallType, Instant rollCallTime,
			TeachingClass teachingClass, Student student) {
		this.rollCallId = rollCallId;
		this.presence = presence;
		this.rollCallType = rollCallType;
		this.rollCallTime = rollCallTime;
		this.teachingClass = teachingClass;
		this.student = student;
	}

	public RollCall copy() {
		return new RollCall(rollCallId, presence, rollCallType, rollCallTime, teachingClass, student);
	}

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

	public TeachingClass getTeachingClass() {
		return teachingClass;
	}

	public void setTeachingClass(TeachingClass teachingClass) {
		this.teachingClass = teachingClass;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void showRollCallRecord() {
		System.out.println(this.student.getStudentId() + " " + this.student.getStudentName() + " " + this.rollCallTime
				+ " " + this.rollCallType + " " + this.presence);
	}
}
