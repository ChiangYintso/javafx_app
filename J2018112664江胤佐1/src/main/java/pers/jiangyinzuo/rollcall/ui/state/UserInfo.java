package main.java.pers.jiangyinzuo.rollcall.ui.state;

import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;

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

	public static void main(String[] args) {
		UserInfo userInfo = UserInfo.getSingleton();
		userInfo.setStudent(new Student(99L, "abc"));
		UserInfo test = UserInfo.getSingleton();
		test.getStudent().welcome();
	}
}
