package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.validator.TeachingClassComparedByStudentIdValidator;
import pers.jiangyinzuo.rollcall.service.validator.TeachingClassComparedByTeacherIdValidator;
import pers.jiangyinzuo.rollcall.service.validator.Validator;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassDaoFileImpl implements TeachingClassDao {

	private static final String FILE_NAME = "teachingClasses.txt";

	@Override
	public void insertTeachingClass(TeachingClass teachingClass) throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		FileHelper.writeSerializableEntity(teachingClass, FILE_NAME);
	}

	@Override
	public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId)
			throws ClassNotFoundException, IOException {
		List<TeachingClass> list = FileHelper.<TeachingClass>readAllSerializableEntities(FILE_NAME);
		List<TeachingClass> results = new ArrayList<>();
		for (TeachingClass cls : list) {
			if (cls.isSelectedThisClass(studentId)) {
				results.add(cls);
			}
		}
		return results;
	}

	@Override
	public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		Validator v = new TeachingClassComparedByTeacherIdValidator();
		return FileHelper.<TeachingClass>readSerializableEntities(FILE_NAME, v, teacherId);
	}

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, IOException, ClassNotFoundException, CustomException {
		TeachingClassDao teachingClassDaoImpl = new TeachingClassDaoFileImpl();

		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(123L, (byte)1, "jyz", "123456", "软件工程"));

		teachingClassDaoImpl.insertTeachingClass(
				new TeachingClass(2L, "高等数学", 201901, 123457, (short) 2, "暂无简介", "3-17周", 32, 123L, studentList));

		teachingClassDaoImpl.insertTeachingClass(
				new TeachingClass(3L, "线性代数", 201901, 121257, (short) 2, "暂无简介", "3-17周", 34, 123L, studentList));
		
		List<TeachingClass> teachingClassList = teachingClassDaoImpl
				.queryTeachingClassesByTeacherId(123L);
		System.out.println(teachingClassList.size());
		for (TeachingClass cls : teachingClassList) {
			System.out.println(cls.getClassName() + cls.getSession());
		}
	}
}
