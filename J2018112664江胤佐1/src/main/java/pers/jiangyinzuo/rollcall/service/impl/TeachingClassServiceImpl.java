package pers.jiangyinzuo.rollcall.service.impl;

import java.util.List;

import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassServiceImpl implements TeachingClassService {
	private TeachingClassDao teachingClassDao = DaoFactory.createDao(TeachingClassDao.class);

	@Override
	public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId) {
		return teachingClassDao.queryTeachingClassesByStudentId(studentId);
	}

	@Override
	public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId) {
		return teachingClassDao.queryTeachingClassesByTeacherId(teacherId);
	}

	public static void main(String[] args) {
		TeachingClassService service = new TeachingClassServiceImpl();
		List<TeachingClass> list = service.queryTeachingClassesByStudentId(123L);
		for (TeachingClass cls : list) {
			System.out.println(cls.getClassName());
		}
	}
}
