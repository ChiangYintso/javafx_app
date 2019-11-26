package pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class Teacher implements Serializable {
	private Long teacherId;
	private String teacherName;
	private String department;
	private String gender;
	private String password;
	private String title;
	private List<TeachingClass> teachingClassList;

	public Teacher(Long teacherId, String teacherName, String department, String gender, String pwd, String title,
				   List<TeachingClass> teachingClassList) {
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.department = department;
		this.gender = gender;
		this.password = pwd;
		this.title = title;
		this.teachingClassList = teachingClassList;
	}

	/**
	 * 用于教师登录验证
	 * 
	 * @param teacherId 教师工号
	 * @param password 密码
	 */
	public Teacher(Long teacherId, String password) {
		this.teacherId = teacherId;
		this.password = password;
	}

	public Teacher(Long teacherId) {
		this.teacherId = teacherId;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String welcome() {
		return "你好, " + this.teacherId + " " + this.teacherName + this.title;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof Teacher && teacherId != null && teacherId.equals(((Teacher) obj).getTeacherId());
	}
}
