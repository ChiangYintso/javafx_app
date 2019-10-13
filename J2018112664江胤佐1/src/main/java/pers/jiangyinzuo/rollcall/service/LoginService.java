package main.java.pers.jiangyinzuo.rollcall.service;

public interface LoginService {
	boolean validateTeacher(Integer teacherId, String pwd);
	
	boolean validateStudent(Integer studentId, String pwd);
}
