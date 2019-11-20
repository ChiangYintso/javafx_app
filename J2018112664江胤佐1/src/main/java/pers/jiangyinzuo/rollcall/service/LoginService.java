package main.java.pers.jiangyinzuo.rollcall.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;

public interface LoginService {
	Teacher teacherLogin(Integer teacherId, String pwd) throws CustomException, FileNotFoundException, IOException, ClassNotFoundException;

	Student studentLogin(Integer studentId, String pwd) throws CustomException, FileNotFoundException, IOException, ClassNotFoundException;
}
