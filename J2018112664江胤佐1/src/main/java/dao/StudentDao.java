package main.java.dao;

import main.java.entity.Student;

public interface StudentDao {
	/**
	 * ����ѧ����Ϣ
	 * 
	 * @param student
	 * @return
	 */
	boolean insertStudent(Student student);
	
	/**
	 * ����ѧ�Ų���ѧ����Ϣ
	 * 
	 * @param studentId
	 * @return
	 */
	Student queryStudent(Integer studentId);
}
