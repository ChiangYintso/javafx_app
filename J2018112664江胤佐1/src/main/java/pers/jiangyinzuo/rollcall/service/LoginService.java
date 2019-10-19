package main.java.pers.jiangyinzuo.rollcall.service;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;

public interface LoginService {
	boolean validateTeacher(Integer teacherId, String pwd);
	
	boolean validateStudent(Integer studentId, String pwd) throws CustomException;
}
