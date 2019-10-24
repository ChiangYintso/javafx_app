package main.java.pers.jiangyinzuo.rollcall.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

public interface TeachingClassService {
	List<TeachingClass> queryTeachingClassesByStudentId(Integer studentId)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException;

	List<TeachingClass> queryTeachingClassesByTeacherId(Integer teacherId)
			throws FileNotFoundException, ClassNotFoundException, IOException, CustomException;
}
