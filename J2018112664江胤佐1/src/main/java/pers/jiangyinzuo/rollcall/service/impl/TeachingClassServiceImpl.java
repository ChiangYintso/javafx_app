package pers.jiangyinzuo.rollcall.service.impl;

import java.io.IOException;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassServiceImpl implements TeachingClassService {
	private TeachingClassDao dao = DaoFactory.createDao(TeachingClassDao.class);

	@Override
	public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId)
			throws ClassNotFoundException, IOException, CustomException {

		return dao.queryTeachingClassesByStudentId(studentId);
	}

	@Override
	public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId)
			throws ClassNotFoundException, IOException, CustomException {
		return dao.queryTeachingClassesByTeacherId(teacherId);
	}

	public static void main(String[] args)
			throws ClassNotFoundException, IOException, CustomException {
		TeachingClassService service = new TeachingClassServiceImpl();
		List<TeachingClass> list = service.queryTeachingClassesByStudentId(123L);
		for (TeachingClass cls : list) {
			System.out.println(cls.getClassName());
		}
	}
}
