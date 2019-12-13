package pers.jiangyinzuo.rollcall.service;


import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;

/**
 * @author Jiang Yinzuo
 */
public interface LoginService {
	Teacher teacherLogin(Long teacherId, String password);

	Student studentLogin(Long studentId, String password);
}
