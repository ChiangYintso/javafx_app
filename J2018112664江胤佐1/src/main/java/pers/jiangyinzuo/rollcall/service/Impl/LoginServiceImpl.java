package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.service.LoginService;
import main.java.pers.jiangyinzuo.rollcall.service.validator.LoginValidator;
import main.java.pers.jiangyinzuo.rollcall.service.validator.Validator;
import main.java.pers.jiangyinzuo.rollcall.helper.FileHelper;

public class LoginServiceImpl implements LoginService {

	/**
	 * @return ��ʦʵ����, ���������򷵻�null
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
	 * @return ѧ��ʵ����, ���������򷵻�null
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
