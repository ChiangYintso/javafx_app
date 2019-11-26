package pers.jiangyinzuo.rollcall.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.TeachingClass;

public interface TeachingClassService {
	List<TeachingClass> queryTeachingClassesByStudentId(Long studentId)
			throws ClassNotFoundException, IOException, CustomException;

	List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId)
			throws ClassNotFoundException, IOException, CustomException;
}
