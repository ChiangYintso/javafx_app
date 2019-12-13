package pers.jiangyinzuo.rollcall.service;

import java.util.List;

import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;

/**
 * @author Jiang Yinzuo
 */
public interface TeachingClassService {
	List<TeachingClass> queryTeachingClassesByStudentId(Long studentId);

	List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId);
}
