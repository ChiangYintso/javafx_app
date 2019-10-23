package main.java.pers.jiangyinzuo.rollcall.service.validator;

import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

public class TeachingClassComparedByStudentIdValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException {
		TeachingClass teachingClass = (TeachingClass) objFromFile;
		Integer studentId = (Integer)obj[0];
		List<Student> studentList = teachingClass.getStudentList();
		if (studentList == null) {
			return false;
		}
		for (Student s : studentList) {
			if (s.getStudentId().equals(studentId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean validate(Object objFromFile, Object obj) {
		TeachingClass teachingClass = (TeachingClass) objFromFile;
		Integer studentId = (Integer)obj;
		List<Student> studentList = teachingClass.getStudentList();
		if (studentList == null) {
			return false;
		}
		for (Student s : studentList) {
			if (s.getStudentId().equals(studentId)) {
				return true;
			}
		}
		return false;
	}

}
