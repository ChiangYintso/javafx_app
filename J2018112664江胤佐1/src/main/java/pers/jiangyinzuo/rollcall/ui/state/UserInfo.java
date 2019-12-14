package pers.jiangyinzuo.rollcall.ui.state;

import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;

/**
 * @author Jiang Yinzuo
 */
public class UserInfo {
	private Student student;
	private Teacher teacher;
	private static UserInfo singleton;

	private UserInfo() {

	}

	/**
	 * µ¥ÀýÄ£Ê½
	 *
	 * @return
	 */
	public static UserInfo getSingleton() {
		if (singleton == null) {
			singleton = new UserInfo();
		}
		return singleton;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
