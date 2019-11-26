package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class StudentDaoFileImpl implements StudentDao {

	@Override
	public void insertStudent(Student student) throws IOException, IllegalArgumentException,
			SecurityException {
		FileHelper.writeSerializableEntity(student, "student.txt");
	}

	@Override
	public Student queryStudent(Long studentId) throws IOException {
		try {
			List<Student> list = FileHelper.readAllSerializableEntities("student.txt");
			for (Student s : list) {
				if (s.getStudentId().equals(studentId)) {
					return s;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, CustomException, SQLException {
		StudentDao s = new StudentDaoFileImpl();
		s.insertStudent(new Student(123L, (byte)1, "jyz", "123456", "Èí¼þ¹¤³Ì"));
		System.out.println(s.queryStudent(123L).getStudentId());
	}
}
