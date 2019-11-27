package pers.jiangyinzuo.rollcall.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.dao.TeacherDao;
import pers.jiangyinzuo.rollcall.dao.fileimpl.TeacherDaoFileImpl;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.Teacher;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.LoginService;
import pers.jiangyinzuo.rollcall.service.validator.LoginValidator;
import pers.jiangyinzuo.rollcall.service.validator.Validator;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class LoginServiceImpl implements LoginService {

	private TeacherDao teacherDao;
	private StudentDao studentDao;

	public LoginServiceImpl() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		teacherDao = DaoFactory.createDao(TeacherDao.class);
		studentDao = DaoFactory.createDao(StudentDao.class);
	}

	/**
	 * @return ��ʦʵ����, ���������򷵻�null
	 * @throws CustomException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public Teacher teacherLogin(Long teacherId, String password) throws ClassNotFoundException, IOException, CustomException {
		try {
			return teacherDao.queryTeacher(teacherId, password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return ѧ��ʵ����, ���������򷵻�null
	 * @throws ClassNotFoundException 
	 */

	@Override
	public Student studentLogin(Long studentId, String password)
			throws IOException, ClassNotFoundException {
		try {
			return studentDao.queryStudent(studentId, password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
