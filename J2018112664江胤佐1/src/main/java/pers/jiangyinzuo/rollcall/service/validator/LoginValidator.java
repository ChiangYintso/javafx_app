package main.java.pers.jiangyinzuo.rollcall.service.validator;

import java.util.ArrayList;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;

public class LoginValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException {
		if (clazz.equals(Teacher.class)) {
			Teacher teacher = (Teacher) objFromFile;
			Teacher teacherSelf = (Teacher) obj[0];
			if (teacher.getTeacherId().equals(teacherSelf.getTeacherId())) {
				if (teacher.getPwd().equals(teacherSelf.getPwd())) {
					return true;
				} else {
					throw new CustomException("密码错误", false);
				}
			}
		} else {
			Student student = (Student) objFromFile;
			Student studentSelf = (Student) obj[0];
			if (student.getStudentId().equals(studentSelf.getStudentId())) {
				if (student.getPwd().equals(studentSelf.getPwd())) {
					return true;
				} else {
					throw new CustomException("密码错误", false);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws CustomException {
		Teacher t = new Teacher(666, "李四", "信息科学与技术学院", "男", "swjtu", "讲师", new ArrayList<>());
		Student s = new Student(1234, "男", "jyz", "软件2018-01班", "123456", "软件工程", new ArrayList<>(),
				new ArrayList<>());
		LoginValidator v = new LoginValidator();
		v.validate(Teacher.class, t);
		v.validate(Student.class, s);
	}

	@Override
	public boolean validate(Object objFromFile, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
