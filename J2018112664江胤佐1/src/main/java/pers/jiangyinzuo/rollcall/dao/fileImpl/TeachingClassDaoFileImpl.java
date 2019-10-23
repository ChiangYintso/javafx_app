package main.java.pers.jiangyinzuo.rollcall.dao.fileImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import main.java.pers.jiangyinzuo.rollcall.entity.Schedule;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.validator.TeachingClassComparedByStudentIdValidator;
import main.java.pers.jiangyinzuo.rollcall.service.validator.TeachingClassComparedByTeacherIdValidator;
import main.java.pers.jiangyinzuo.rollcall.service.validator.Validator;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class TeachingClassDaoFileImpl implements TeachingClassDao {

	private static final String FILE_NAME = "teachingClasses.txt";

	@Override
	public void insertTeachingClass(TeachingClass teachingClass) throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		AppFile.writeSerializableEntity(teachingClass, FILE_NAME);
	}

	@Override
	public List<TeachingClass> queryTeachingClassesByStudentId(Integer studentId)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		Validator v = new TeachingClassComparedByStudentIdValidator();
		return AppFile.<TeachingClass>readSerializableEntities(FILE_NAME, v, studentId);
	}

	@Override
	public List<TeachingClass> queryTeachingClassesByTeacherId(Integer teacherId)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
		Validator v = new TeachingClassComparedByTeacherIdValidator();
		return AppFile.<TeachingClass>readSerializableEntities(FILE_NAME, v, teacherId);
	}

	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, IOException, ClassNotFoundException, CustomException {
		TeachingClassDao teachingClassDaoImpl = new TeachingClassDaoFileImpl();

		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(123, "男", "jyz", "软件2018-01班", "123456", "软件工程"));

		List<Schedule> scheduleList = new ArrayList<>();

		teachingClassDaoImpl.insertTeachingClass(new TeachingClass(Integer.valueOf(1), "数学分析", 201901, 6666, (short) 2,
				"", 123, scheduleList, studentList));

		List<TeachingClass> teachingClassList = teachingClassDaoImpl.queryTeachingClassesByTeacherId(Integer.valueOf(123));
		System.out.println(teachingClassList.size());
		for (TeachingClass cls : teachingClassList) {
			System.out.println(cls.getClassName());
		}
	}
}
