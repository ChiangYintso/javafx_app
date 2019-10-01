package main.java.com.call.rollHelper.entity;

import java.util.List;

public class TeachingClass {
	private Long classId;
	private String className;
	private String semester;
	private String teachingPeriod;
	private String classRoom;
	private List<Student> studentList;
	
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getTeachingPeriod() {
		return teachingPeriod;
	}
	public void setTeachingPeriod(String teachingPeriod) {
		this.teachingPeriod = teachingPeriod;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	
	
}
