package pers.jiangyinzuo.rollcall.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Teacher;

public interface TeacherDao {
	/**
	 * 插入教师
	 * 
	 * @param teacher
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	void insertTeacher(Teacher teacher) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException;

	/**
	 * 根据id查找老师
	 * 
	 * @param teacherId
	 * @return
	 * @throws CustomException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	Teacher queryTeacher(Long teacherId) throws CustomException, FileNotFoundException, IOException;

	/**
	 * 根据账号密码查找老师, 用于登录验证
	 * @param teacherId
	 * @param password
	 * @return
	 */
	Teacher queryTeacher(Long teacherId, String password) throws IOException, ClassNotFoundException;
}
