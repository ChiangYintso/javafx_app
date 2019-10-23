package main.java.pers.jiangyinzuo.rollcall.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

public interface TeachingClassDao {
	void insertTeachingClass(TeachingClass teachingClass) throws IOException, IllegalAccessException, IllegalArgumentException,
	InvocationTargetException, NoSuchMethodException, SecurityException;;
	
	List<TeachingClass> queryTeachingClassesByStudentId(Integer studentId) throws FileNotFoundException, ClassNotFoundException, IOException, CustomException;
	
	List<TeachingClass> queryTeachingClassesByTeacherId(Integer teacher) throws FileNotFoundException, ClassNotFoundException, IOException, CustomException;
}
