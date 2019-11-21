package main.java.pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable {
	private Integer teacherId;
	private String teacherName;
	private String department;
	private String gender;
	private String pwd;
	private String title;
	private List<TeachingClass> teachingClassList;

	public Teacher(Integer teacherId, String teacherName, String department, String gender, String pwd, String title,
			List<TeachingClass> teachingClassList) {
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.department = department;
		this.gender = gender;
		this.pwd = pwd;
		this.title = title;
		this.teachingClassList = teachingClassList;
	}

	/**
	 * 用于教师登录验证
	 * 
	 * @param teacherId
	 * @param pwd
	 */
	public Teacher(Integer teacherId, String pwd) {
		this.teacherId = teacherId;
		this.pwd = pwd;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TeachingClass> getTeachingClassList() {
		return teachingClassList;
	}

	public void setTeachingClassList(List<TeachingClass> teachingClassList) {
		this.teachingClassList = teachingClassList;
	}

	public void welcome() {
		System.out.println("你好, " + this.teacherId + " " + this.teacherName + this.title);
	}
}
