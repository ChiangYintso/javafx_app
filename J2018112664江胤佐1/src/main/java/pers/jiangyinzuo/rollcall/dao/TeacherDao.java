package pers.jiangyinzuo.rollcall.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Teacher;

public interface TeacherDao {
	/**
	 * �����ʦ
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
	 * ����id������ʦ
	 * 
	 * @param teacherId
	 * @return
	 * @throws CustomException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	Teacher queryTeacher(Long teacherId) throws CustomException, FileNotFoundException, IOException;
}
