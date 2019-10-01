package main.java.com.call.rollHelper.entity;

import java.util.List;

public class Teacher {
	private Long teacherId;
	private String teacherName;
	private List<TeachingClass> teachingClassList;

	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public List<TeachingClass> getTeachingClassList() {
		return teachingClassList;
	}
	public void setTeachingClassList(List<TeachingClass> teachingClassList) {
		this.teachingClassList = teachingClassList;
	}
}
