package main.java.pers.jiangyinzuo.rollHelper.entity;

import java.util.List;

public class TeachingClass {
	private Integer classId;
	private String className;
	private Integer semester;
	private Integer courseCode;
	private Short credit;
	private String intro;
	private List<TeachingClass> teachingClassList;
	private List<Schedule> scheduleList;
	private List<Student> studentList;

	public Integer getClassId() {
		return classId;
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

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
}
