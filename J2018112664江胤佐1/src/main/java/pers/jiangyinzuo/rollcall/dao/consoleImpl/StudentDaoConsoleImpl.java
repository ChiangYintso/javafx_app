package main.java.pers.jiangyinzuo.rollcall.dao.consoleImpl;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.StudentDao;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class StudentDaoConsoleImpl implements StudentDao {

	@Override
	public void insertStudent(Student student) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		AppFile.writeEntity(student);
	}

	@Override
	public Student queryStudent(Integer studentId) throws FileNotFoundException, IOException, CustomException {
		try (FileInputStream fileInputStream = new FileInputStream(AppFile.getAppPath() + "student.txt");
				InputStreamReader reader = new InputStreamReader(fileInputStream,"GBK");
				BufferedReader br = new BufferedReader(reader);) {
			String tempStudentId = studentId.toString();
			String line;
			while((line = br.readLine()) != null) {
				if (line.startsWith(tempStudentId)) {
					System.out.println(line);
					return null;
				}
			}

		} catch (FileNotFoundException e) {
			throw new CustomException("�Ҳ����ļ�student.txt", false);
		} catch (IOException e) {
			throw new CustomException("IO�쳣", false);
		}
		return null;
	}

	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, CustomException {
		StudentDaoConsoleImpl s = new StudentDaoConsoleImpl();
		s.insertStudent(new Student(123, "��", "jyz", "���2018-01��", "123456", "�������"));
		System.out.println("query:");
		s.queryStudent(123);
	}
}
