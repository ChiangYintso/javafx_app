package main.java.pers.jiangyinzuo.rollcall.service.validator;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;

public interface Validator {
	boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException;
}
