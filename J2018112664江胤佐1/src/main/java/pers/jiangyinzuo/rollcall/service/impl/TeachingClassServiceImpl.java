package pers.jiangyinzuo.rollcall.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.dao.fileimpl.TeachingClassDaoFileImpl;
import pers.jiangyinzuo.rollcall.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassServiceImpl implements TeachingClassService {

	@Override
	public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		TeachingClassDao dao = new TeachingClassDaoFileImpl();
		return dao.queryTeachingClassesByStudentId(studentId);
	}

	@Override
	public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		TeachingClassDao dao = new TeachingClassDaoFileImpl();
		return dao.queryTeachingClassesByTeacherId(teacherId);
	}

	public static void main(String[] args)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		TeachingClassService service = new TeachingClassServiceImpl();
		List<TeachingClass> list = service.queryTeachingClassesByStudentId(123L);
		for (TeachingClass cls : list) {
			System.out.println(cls.getClassName());
		}
	}
}
