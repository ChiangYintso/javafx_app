package pers.jiangyinzuo.rollcall.dao;


import pers.jiangyinzuo.rollcall.domain.entity.Teacher;

/**
 * @author Jiang Yinzuo
 */
public interface TeacherDao {
	/**
	 * �����ʦ
	 * 
	 * @param teacher
	 */
	void insertTeacher(Teacher teacher);

	/**
	 * ����id������ʦ
	 * 
	 * @param teacherId
	 * @return
	 */
	Teacher queryTeacher(Long teacherId);

	/**
	 * �����˺����������ʦ, ���ڵ�¼��֤
	 * @param teacherId
	 * @param password
	 * @return
	 */
	Teacher queryTeacher(Long teacherId, String password);
}
