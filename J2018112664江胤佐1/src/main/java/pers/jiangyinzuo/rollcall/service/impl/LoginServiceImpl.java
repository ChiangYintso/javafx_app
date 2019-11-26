package pers.jiangyinzuo.rollcall.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.Teacher;
import pers.jiangyinzuo.rollcall.service.LoginService;
import pers.jiangyinzuo.rollcall.service.validator.LoginValidator;
import pers.jiangyinzuo.rollcall.service.validator.Validator;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

public class LoginServiceImpl implements LoginService {

	/**
	 * @return 教师实体类, 若不存在则返回null
	 * @throws CustomException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public Teacher teacherLogin(Long teacherId, String pwd) throws ClassNotFoundException, IOException, CustomException {
		try {
			Validator v = new LoginValidator();
			Teacher teacher = new Teacher(teacherId, pwd);
			Teacher teacherFromFile = (Teacher) FileHelper.readSerializableEntity("teachers.txt", v, Teacher.class,
					teacher);
			return teacherFromFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return 学生实体类, 若不存在则返回null
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("finally")
	@Override
	public Student studentLogin(Long studentId, String pwd)
			throws CustomException, FileNotFoundException, IOException, ClassNotFoundException {
		try {
			Validator v = new LoginValidator();
			Student student = new Student(studentId, pwd);
			Student studentFromFile = (Student) FileHelper.readSerializableEntity("student.txt", v, Student.class,
					student);
			return studentFromFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
