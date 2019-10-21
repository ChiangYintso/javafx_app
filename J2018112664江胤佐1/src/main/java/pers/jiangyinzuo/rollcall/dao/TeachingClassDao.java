package main.java.pers.jiangyinzuo.rollcall.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

public interface TeachingClassDao {
	void insertTeachingClassDao(TeachingClass teachingClass) throws IOException, IllegalAccessException, IllegalArgumentException,
	InvocationTargetException, NoSuchMethodException, SecurityException;;
	
	void queryTeachingClassesByStudentId(Integer studentId);
	
	void queryTeachingClassesByTeacherId(Integer teacher);
}
