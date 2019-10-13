package main.java.pers.jiangyinzuo.rollcall.dao;

import main.java.pers.jiangyinzuo.rollcall.entity.Student;

public interface StudentDao {
	/**
	 * 插入学生信息
	 * 
	 * @param student
	 * @return
	 */
	boolean insertStudent(Student student);
	
	/**
	 * 根据学号查找学生信息
	 * 
	 * @param studentId
	 * @return
	 */
	Student queryStudent(Integer studentId);
}
