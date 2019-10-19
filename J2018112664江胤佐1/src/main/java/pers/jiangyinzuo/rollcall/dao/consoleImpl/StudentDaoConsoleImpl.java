package main.java.pers.jiangyinzuo.rollcall.dao.consoleImpl;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.StudentDao;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;


public class StudentDaoConsoleImpl implements StudentDao {

	@Override
	public void insertStudent(Student student) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		AppFile.writeEntity(student);
	}

	@Override
	public Student queryStudent(Integer studentId) throws FileNotFoundException, IOException, CustomException {
		try (FileInputStream fileInputStream = new FileInputStream(AppFile.getAppPath() + "student.txt")) {
			String tempStudentId = studentId.toString();
			StringBuilder tempStr;
			
			tempStr = AppFile.getLine(fileInputStream);
			if (tempStr.equals(tempStudentId)) {
				System.out.println("成功");
				return null;
			}
		} catch (FileNotFoundException e) {
			throw new CustomException("找不到文件student.txt", false);
		} catch (IOException e) {
			throw new CustomException("IO异常", false);
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		StudentDaoConsoleImpl s = new StudentDaoConsoleImpl();
		s.insertStudent(new Student(123, "男", "jyz", "软件2018-01班", "123456", "软件工程"));
	}
}
