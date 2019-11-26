package pers.jiangyinzuo.rollcall.service.validator;

import pers.jiangyinzuo.rollcall.common.CustomException;

public interface Validator {
	boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException;

	boolean validate(Object objFromFile, Object obj);
}
