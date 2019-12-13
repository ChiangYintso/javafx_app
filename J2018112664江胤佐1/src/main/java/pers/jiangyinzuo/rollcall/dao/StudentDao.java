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
}
