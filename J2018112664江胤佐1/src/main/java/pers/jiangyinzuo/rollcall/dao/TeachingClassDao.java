package pers.jiangyinzuo.rollcall.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;

public interface TeachingClassDao {
	void insertTeachingClass(TeachingClass teachingClass) throws IOException, IllegalAccessException, IllegalArgumentException,
	InvocationTargetException, NoSuchMethodException, SecurityException;;
	
	List<TeachingClass> queryTeachingClassesByStudentId(Long studentId) throws FileNotFoundException, ClassNotFoundException, IOException, CustomException;
	
	List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId) throws FileNotFoundException, ClassNotFoundException, IOException, CustomException;
}
