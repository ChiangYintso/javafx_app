package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.service.LoginService;
import main.java.pers.jiangyinzuo.rollcall.service.validator.LoginValidator;
import main.java.pers.jiangyinzuo.rollcall.service.validator.Validator;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class LoginServiceImpl implements LoginService {

	/**
	 * @return ��ʦʵ����, ���������򷵻�null
	 */
	@Override
	public Teacher teacherLogin(Integer teacherId, String pwd) {
		try {
			Validator v = new LoginValidator();
			Teacher teacher = new Teacher(teacherId, pwd);
			Teacher teacherFromFile = (Teacher) AppFile.readSerializableEntity("teachers.txt", v, Teacher.class,
					teacher);
			return teacherFromFile;
		} catch (CustomException e) {
			System.out.println("�˺Ų�����");
		} catch (ClassNotFoundException e) {
			System.out.println("�˺Ų�����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return ѧ��ʵ����, ���������򷵻�null
	 */
	@SuppressWarnings("finally")
	@Override
	public Student studentLogin(Integer studentId, String pwd)
			throws CustomException, FileNotFoundException, IOException {
		try {
			Validator v = new LoginValidator();
			Student student = new Student(studentId, pwd);
			Student studentFromFile = (Student) AppFile.readSerializableEntity("student.txt", v, Student.class,
					student);
			return studentFromFile;
		} catch (CustomException e) {
			System.out.println("�˺Ų�����");
		} catch (ClassNotFoundException e) {
			System.out.println("�˺Ų�����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
