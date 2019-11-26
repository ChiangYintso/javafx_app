package pers.jiangyinzuo.rollcall.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.service.StudentService;
import pers.jiangyinzuo.rollcall.helper.FileHelper;
import pers.jiangyinzuo.rollcall.service.validator.Validator;

/**
 * @author Jiang Yinzuo
 */
public class StudentServiceImpl implements StudentService {
	public static void main(String[] args) throws IOException, ClassNotFoundException, CustomException {
		StudentsEqualValidator v = new StudentsEqualValidator();
		Student student = new Student(1234L, (byte)1, "jyz", "123456", "Èí¼þ¹¤³Ì", new ArrayList<>(),
				new ArrayList<>());
		FileHelper.writeSerializableEntity(student, "student.txt");
		Student result = (Student) FileHelper.readSerializableEntity("student.txt", v, student.getClass(), student);
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

	@Override
	public boolean validate(Object objFromFile, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}