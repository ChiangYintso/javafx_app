package pers.jiangyinzuo.rollcall.service.impl;

import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.dao.TeacherDao;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.LoginService;

/**
 * @author Jiang Yinzuo
 */
public class LoginServiceImpl implements LoginService {

	private TeacherDao teacherDao;
	private StudentDao studentDao;

	public LoginServiceImpl() {
		teacherDao = DaoFactory.createDao(TeacherDao.class);
		studentDao = DaoFactory.createDao(StudentDao.class);
	}

	@Override
	public Teacher teacherLogin(Long teacherId, String password) {
		return teacherDao.queryTeacher(teacherId, password);
	}

	@Override
	public Student studentLogin(Long studentId, String password) {
		return studentDao.queryStudent(studentId, password);
	}
}
