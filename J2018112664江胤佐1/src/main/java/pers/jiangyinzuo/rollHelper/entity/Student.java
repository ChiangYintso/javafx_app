package main.java.pers.jiangyinzuo.rollHelper.entity;

import java.util.List;

public class Student {
	private Integer studentId;
	private String gender;
	private String studentName;
	private String adminClass;
	private String pwd;
	private String major;
	private List<TeachingClass> teachingClassList;
	private List<Schedule> scheduleList;
	private List<RollCall> rollCallList;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAdminClass() {
		return adminClass;
	}

	public void setAdminClass(String adminClass) {
		this.adminClass = adminClass;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<TeachingClass> getTeachingClassList() {
		return teachingClassList;
	}

	public void setTeachingClassList(List<TeachingClass> teachingClassList) {
		this.teachingClassList = teachingClassList;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public List<RollCall> getRollCallList() {
		return rollCallList;
	}

	public void setRollCallList(List<RollCall> rollCallList) {
		this.rollCallList = rollCallList;
	}
}
