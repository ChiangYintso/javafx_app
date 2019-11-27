package pers.jiangyinzuo.rollcall.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;

public interface StudentDao {
	/**
	 * ����ѧ����Ϣ
	 * 
	 * @param student ѧ��
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	void insertStudent(Student student) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	/**
	 * ����ѧ�Ų���ѧ����Ϣ
	 * 
	 * @param studentId ѧ��
	 * @return �����ڣ�����ѧ��ʵ���ࣻ�������ڣ�����null
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	Student queryStudent(Long studentId) throws CustomException, FileNotFoundException, IOException;

	/**
	 * ����ѧ�ź��������ѧ��, ���ڵ�¼
	 * @param studentId ѧ��
	 * @param password ����
	 * @return �����ڣ�����ѧ��ʵ���ࣻ�������ڣ�����null
	 */
	Student queryStudent(Long studentId, String password) throws IOException, ClassNotFoundException;
}
