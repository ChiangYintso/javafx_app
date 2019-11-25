package main.java.pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Jiang Yinzuo
 */
public class RollCall implements Serializable {
	private Long rollCallId;

	/**
	 *  已到、未到、迟到、请假、早退
	 */
	private String presence;
	private String rollCallType;
	private Instant rollCallTime;
	private TeachingClass teachingClass;
	private Student student;

	public RollCall(Long rollCallId, String presence, String rollCallType, Instant rollCallTime,
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

	public Long getRollCallId() {
		return rollCallId;
	}

	public void setRollCallId(Long rollCallId) {
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

	public String getRollCallInfo() {
		return this.student.getStudentId() + " " + this.student.getStudentName() + " " + this.rollCallTime.toString()
				+ " " + this.rollCallType + " " + this.presence;
	}

	/**
	 * 根据rollCallId判断两个实体类是否相等
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		return obj == this || obj instanceof RollCall && rollCallId != null && rollCallId.equals(((RollCall) obj).getRollCallId());
	}
}
