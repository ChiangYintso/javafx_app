package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.service.TeacherService;
import main.java.pers.jiangyinzuo.rollcall.service.validator.Validator;

public class TeacherServiceImpl implements TeacherService {
	
	public static void main(String[] args) {
		TeacherIDEqualsValidator v = new TeacherIDEqualsValidator();
	}

	
}

class TeacherIDEqualsValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) {
		Teacher teacher = (Teacher) objFromFile;
		Teacher teacherSelf = (Teacher) obj[0];
		return teacher.getTeacherId().equals(teacherSelf.getTeacherId());
	}
	
}