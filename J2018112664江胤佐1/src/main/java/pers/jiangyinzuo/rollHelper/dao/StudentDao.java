package main.java.pers.jiangyinzuo.rollHelper.dao;

import main.java.pers.jiangyinzuo.rollHelper.entity.Student;

public interface StudentDao {
	/**
	 * ����ѧ����Ϣ
	 * 
	 * @param student
	 * @return
	 */
	int insertStudent(Student student);
	
	/**
	 * ����ѧ�Ų���ѧ����Ϣ
	 * 
	 * @param studentId
	 * @return
	 */
	Student queryStudent(Integer studentId);
}
