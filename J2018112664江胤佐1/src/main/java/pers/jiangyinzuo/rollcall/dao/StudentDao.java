package pers.jiangyinzuo.rollcall.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.domain.entity.Student;

/**
 * @author Jiang Yinzuo
 */
public interface StudentDao {
	/**
	 * 插入学生信息
	 * 
	 * @param student 学生
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
	 * @param studentId 学号
	 * @return 若存在，返回学生实体类；若不存在，返回null
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	Student queryStudent(Long studentId) throws CustomException, FileNotFoundException, IOException;

	/**
	 * 根据学号和密码查找学生, 用于登录
	 * @param studentId 学号
	 * @param password 密码
	 * @return 若存在，返回学生实体类；若不存在，返回null
	 */
	Student queryStudent(Long studentId, String password) throws IOException, ClassNotFoundException;
}
