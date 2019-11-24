package main.java.pers.jiangyinzuo.rollcall.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

public interface TeachingClassService {
	List<TeachingClass> queryTeachingClassesByStudentId(Long studentId)
			throws ClassNotFoundException, IOException, CustomException;

	List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId)
			throws ClassNotFoundException, IOException, CustomException;
}
