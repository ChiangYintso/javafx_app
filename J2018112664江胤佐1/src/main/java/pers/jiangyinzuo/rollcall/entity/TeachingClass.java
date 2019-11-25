package main.java.pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClass implements Serializable {
	private Long classId;
	private String className;
	private Integer semester;
	private Integer courseCode;
	private Short credit;
	private String intro;
	private String weeks;
	private Integer session;
	private Long teacherId;
	private List<Student> studentList;

	public Long getClassId() {
		return classId;
	}

	public TeachingClass(Long classId, String className, Integer semester, Integer courseCode, Short credit,
						 String intro, String week, Integer session, Long teacherId,
						 List<Student> studentList) {
		this.classId = classId;
		this.className = className;
		this.semester = semester;
		this.courseCode = courseCode;
		this.credit = credit;
		this.intro = intro;
		this.teacherId = teacherId;
		this.studentList = studentList;
		this.weeks = week;
		this.session = session;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public Integer getSession() {
		return session;
	}

	public void setSession(Integer session) {
		this.session = session;
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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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
				+ ", studentList=" + studentList + "]";
	}

	public String getTeachingClassInfo() {
		return "课程id: " + this.classId + ", 课程名称: " + this.className + ", 开课学期: " + this.semester + ", 学分: "
				+ this.credit + " 教师id: " + this.teacherId + "课程简介: " + this.intro;
	}

	public String getSchedule() {
		return this.className + " 周" + this.session / 10 + "第" + this.session % 10 + "讲";
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || obj instanceof TeachingClass && classId != null && classId.equals(((TeachingClass) obj).getClassId());
	}
}
