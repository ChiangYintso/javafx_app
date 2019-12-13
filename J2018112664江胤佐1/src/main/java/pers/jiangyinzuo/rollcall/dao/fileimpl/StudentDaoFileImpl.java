package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class StudentDaoFileImpl implements StudentDao {

	private static final String FILE_NAME = "student.txt";

	@Override
	public void insertStudent(Student student) {
		FileHelper.writeSerializableEntity(student, FILE_NAME);
	}

	@Override
	public Student queryStudent(Long studentId) {
		List<Student> list = FileHelper.readAllSerializableEntities("student.txt");
		for (Student s : list) {
			if (s.getStudentId().equals(studentId)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Student queryStudent(Long studentId, String password) {
		Student student =  FileHelper.readSerializableEntity(FILE_NAME, new Student.Builder()
				.studentId(studentId)
				.password(password)
				.build());
		if (student == null || password != null && !password.equals(student.getPwd())) {
			return null;
		} else {
			return student;
		}
	}

	public static void main(String[] args) {
		StudentDao s = new StudentDaoFileImpl();
		s.insertStudent(new Student(123L, true, "jyz", "123456", "�������"));
		System.out.println(s.queryStudent(123L).getStudentId());
	}
}
