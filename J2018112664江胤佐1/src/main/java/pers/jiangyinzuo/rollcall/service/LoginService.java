package pers.jiangyinzuo.rollcall.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.Teacher;

public interface LoginService {
	Teacher teacherLogin(Long teacherId, String pwd) throws CustomException, FileNotFoundException, IOException, ClassNotFoundException;

	Student studentLogin(Long studentId, String pwd) throws CustomException, FileNotFoundException, IOException, ClassNotFoundException;
}
