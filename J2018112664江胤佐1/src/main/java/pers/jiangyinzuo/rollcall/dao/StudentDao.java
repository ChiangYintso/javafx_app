package main.java.pers.jiangyinzuo.rollcall.dao;

import main.java.pers.jiangyinzuo.rollcall.entity.Student;

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
