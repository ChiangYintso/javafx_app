package pers.jiangyinzuo.rollcall.dao;

import pers.jiangyinzuo.rollcall.domain.entity.Student;

/**
 * @author Jiang Yinzuo
 */
public interface StudentDao {
	/**
	 * ����ѧ����Ϣ
	 * 
	 * @param student ѧ��
	 * @return
	 */
	void insertStudent(Student student);
	
	/**
	 * ����ѧ�Ų���ѧ����Ϣ
	 * 
	 * @param studentId ѧ��
	 * @return �����ڣ�����ѧ��ʵ���ࣻ�������ڣ�����null
	 */
	Student queryStudent(Long studentId);

	/**
	 * ����ѧ�ź��������ѧ��, ���ڵ�¼
	 * @param studentId ѧ��
	 * @param password ����
	 * @return �����ڣ�����ѧ��ʵ���ࣻ�������ڣ�����null
	 */
	Student queryStudent(Long studentId, String password);

	void updateStudent(Student student);

	void deleteStudent(Long studentId);
}
