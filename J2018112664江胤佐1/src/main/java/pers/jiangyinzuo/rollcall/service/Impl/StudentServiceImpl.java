package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import java.io.IOException;
import java.util.ArrayList;

import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.service.StudentService;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;
import main.java.pers.jiangyinzuo.rollcall.util.Validator;

public class StudentServiceImpl implements StudentService {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		StudentsEqualValidator v = new StudentsEqualValidator();
		Student student = new Student(1234, "男", "jyz", "软件2018-01班", "123456", "软件工程", new ArrayList<>(),
				new ArrayList<>(), new ArrayList<>());
		AppFile.writeSerializableEntity(student, "student.txt");
		Student result = (Student) AppFile.readSerializableEntity("student.txt", v, student.getClass(), student);
		System.out.println(result.getStudentId());
	}
}

class StudentsEqualValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) {
		Student student = (Student) objFromFile;
		Student studentSelf = (Student) obj[0];
		return studentSelf.getStudentId().equals(student.getStudentId());
	}
}