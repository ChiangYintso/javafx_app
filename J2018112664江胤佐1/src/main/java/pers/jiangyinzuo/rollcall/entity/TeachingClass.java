package main.java.pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeachingClass implements Serializable {
	private Integer classId;
	private String className;
	private Integer semester;
	private Integer courseCode;
	private Short credit;
	private String intro;
	private Integer teacherId;
	private List<Schedule> scheduleList;
	private List<Student> studentList;

	public Integer getClassId() {
		return classId;
	}

	public TeachingClass(Integer classId, String className, Integer semester, Integer courseCode, Short credit,
			String intro, Integer teacherId, List<Schedule> scheduleList,
			List<Student> studentList) {
		this.classId = classId;
		this.className = className;
		this.semester = semester;
		this.courseCode = courseCode;
		this.credit = credit;
		this.intro = intro;
		this.teacherId = teacherId;
		this.scheduleList = scheduleList;
		this.studentList = studentList;
	}
	
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(Integer courseCode) {
		this.courseCode = courseCode;
	}

	public Short getCredit() {
		return credit;
	}

	public void setCredit(Short credit) {
		this.credit = credit;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "TeachingClass [classId=" + classId + ", className=" + className + ", semester=" + semester
				+ ", courseCode=" + courseCode + ", credit=" + credit + ", intro=" + intro + ", teacherId=" + teacherId
				+ ", scheduleList=" + scheduleList + ", studentList=" + studentList + "]";
	}
	
	public void showTeachingClassInfo() {
		System.out.println("课程id: " + this.classId + ", 课程名称: " + this.className
				+ ", 开课学期: " + this.semester + ", 学分: " + this.credit);
	}
}
