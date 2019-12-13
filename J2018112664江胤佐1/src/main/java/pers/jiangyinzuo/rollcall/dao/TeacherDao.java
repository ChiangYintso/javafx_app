package pers.jiangyinzuo.rollcall.dao;


import pers.jiangyinzuo.rollcall.domain.entity.Teacher;

/**
 * @author Jiang Yinzuo
 */
public interface TeacherDao {
	/**
	 * 插入教师
	 * 
	 * @param teacher
	 */
	void insertTeacher(Teacher teacher);

	/**
	 * 根据id查找老师
	 * 
	 * @param teacherId
	 * @return
	 */
	Teacher queryTeacher(Long teacherId);

	/**
	 * 根据账号密码查找老师, 用于登录验证
	 * @param teacherId
	 * @param password
	 * @return
	 */
	Teacher queryTeacher(Long teacherId, String password);
}
