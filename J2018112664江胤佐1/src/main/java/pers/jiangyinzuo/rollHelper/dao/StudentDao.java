package main.java.pers.jiangyinzuo.rollHelper.dao;

import main.java.pers.jiangyinzuo.rollHelper.entity.Student;

public interface StudentDao {
	/**
	 * 插入学生信息
	 * 
	 * @param student
	 * @return
	 */
	int insertStudent(Student student);
	
	/**
	 * 根据学号查找学生信息
	 * 
	 * @param studentId
	 * @return
	 */
	Student queryStudent(Integer studentId);
}
