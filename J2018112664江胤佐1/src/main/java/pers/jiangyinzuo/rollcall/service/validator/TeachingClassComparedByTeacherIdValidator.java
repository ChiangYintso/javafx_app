package main.java.pers.jiangyinzuo.rollcall.service.validator;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

public class TeachingClassComparedByTeacherIdValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException {
		Integer teacherId = (Integer)obj[0];
		TeachingClass teachingClass = (TeachingClass) objFromFile;
		return teachingClass.getTeacherId().equals(teacherId);
	}
	
	@Override
	public boolean validate(Object objFromFile, Object obj) {
		Integer teacherId = (Integer)obj;
		TeachingClass teachingClass = (TeachingClass) objFromFile;
		return teachingClass.getTeacherId().equals(teacherId);
	}
}
