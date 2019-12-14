package pers.jiangyinzuo.rollcall.dao;

import java.util.List;

import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;

/**
 * @author Jiang Yinzuo
 */
public interface TeachingClassDao {
	void insertTeachingClass(TeachingClass teachingClass);
	
	List<TeachingClass> queryTeachingClassesByStudentId(Long studentId);
	
	List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId);

	List<Student> queryStudentList(Long classId);
}
