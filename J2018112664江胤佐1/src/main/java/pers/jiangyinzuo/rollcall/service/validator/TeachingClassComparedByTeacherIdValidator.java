package pers.jiangyinzuo.rollcall.service.validator;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.TeachingClass;

public class TeachingClassComparedByTeacherIdValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException {
		Long teacherId = (Long) obj[0];
		TeachingClass teachingClass = (TeachingClass) objFromFile;
		return teachingClass.getTeacherId().equals(teacherId);
	}
	
	@Override
	public boolean validate(Object objFromFile, Object obj) {
		Long teacherId = (Long) obj;
		TeachingClass teachingClass = (TeachingClass) objFromFile;
		return teachingClass.getTeacherId().equals(teacherId);
	}
}
