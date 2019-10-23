package main.java.pers.jiangyinzuo.rollcall.service.validator;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;

public class RollCallTeachingClassIdValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException {
		Integer teachingClassId = (Integer)obj[0];
		RollCall rollCall = (RollCall) objFromFile;
		return rollCall.getTeachingClass().getClassId().equals(teachingClassId);
	}

	@Override
	public boolean validate(Object objFromFile, Object obj) {
		Integer teachingClassId = (Integer)obj;
		RollCall rollCall = (RollCall) objFromFile;
		return rollCall.getTeachingClass().getClassId().equals(teachingClassId);
	}

}
