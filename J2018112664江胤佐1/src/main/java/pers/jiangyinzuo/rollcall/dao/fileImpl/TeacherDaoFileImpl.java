package main.java.pers.jiangyinzuo.rollcall.dao.fileImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.TeacherDao;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class TeacherDaoFileImpl implements TeacherDao {

	@Override
	public void insertTeacher(Teacher teacher) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		AppFile.writeSerializableEntity(teacher, "teachers.txt");
	}

	@Override
	public Teacher queryTeacherById(Integer teacherId) throws CustomException, FileNotFoundException, IOException {
		return null;
	}

	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, CustomException {
		TeacherDao s = new TeacherDaoFileImpl();
		s.insertTeacher(new Teacher(123, "张三", "信息科学与技术学院", "男", "123456", "讲师", new ArrayList<>()));
		
	}
}
