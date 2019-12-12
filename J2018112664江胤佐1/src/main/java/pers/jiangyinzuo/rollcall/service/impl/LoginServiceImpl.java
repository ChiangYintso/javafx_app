package pers.jiangyinzuo.rollcall.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import pers.jiangyinzuo.rollcall.common.CustomException;
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

	/**
	 * @return 教师实体类, 若不存在则返回null
	 * @throws CustomException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 *
	 */
	@Override
	public Teacher teacherLogin(Long teacherId, String password) {
		try {
			return teacherDao.queryTeacher(teacherId, password);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return 学生实体类, 若不存在则返回null
	 * @throws ClassNotFoundException 
	 */
	@Override
	public Student studentLogin(Long studentId, String password) {
		try {
			return studentDao.queryStudent(studentId, password);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
