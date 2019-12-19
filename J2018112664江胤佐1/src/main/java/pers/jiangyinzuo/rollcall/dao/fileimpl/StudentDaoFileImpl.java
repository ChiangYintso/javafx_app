package pers.jiangyinzuo.rollcall.dao.fileimpl;

import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

import java.util.List;

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

	@Override
	public void updateStudent(Student student) {
		List<Student> list = FileHelper.readAllSerializableEntities(FILE_NAME);
		for (Student s : list) {
			if (s.getStudentId().equals(student.getStudentId())) {
				list.remove(s);
				list.add(student);
				break;
			}
		}
		FileHelper.bulkWriteSerializableEntities(FILE_NAME, list, false);
	}

	@Override
	public void deleteStudent(Long studentId) {
		List<Student> list = FileHelper.readAllSerializableEntities(FILE_NAME);
		for (Student s : list) {
			if (s.getStudentId().equals(studentId)) {
				list.remove(s);
				break;
			}
		}
		FileHelper.bulkWriteSerializableEntities(FILE_NAME, list, false);
	}

	public static void main(String[] args) {
		StudentDao s = new StudentDaoFileImpl();
		s.insertStudent(new Student(123L, true, "jyz", "123456", "Èí¼þ¹¤³Ì"));
		System.out.println(s.queryStudent(123L).getStudentId());
	}
}
