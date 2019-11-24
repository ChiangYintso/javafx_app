package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import main.java.pers.jiangyinzuo.rollcall.dao.fileimpl.TeachingClassDaoFileImpl;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;

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
