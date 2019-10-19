package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.service.LoginService;

public class LoginServiceImpl implements LoginService {

	@Override
	public boolean validateTeacher(Integer teacherId, String pwd) {
		// TODO
		return true;
	}

	@Override
	public boolean validateStudent(Integer studentId, String pwd) throws CustomException {
		return false;
	}

}
