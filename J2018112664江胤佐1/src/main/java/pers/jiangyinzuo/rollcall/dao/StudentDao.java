package pers.jiangyinzuo.rollcall.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;

public interface StudentDao {
	/**
	 * 插入学生信息
	 * 
	 * @param student
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	void insertStudent(Student student) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	/**
	 * 根据学号查找学生信息
	 * 
	 * @param studentId
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	Student queryStudent(Long studentId) throws CustomException, FileNotFoundException, IOException;
}
