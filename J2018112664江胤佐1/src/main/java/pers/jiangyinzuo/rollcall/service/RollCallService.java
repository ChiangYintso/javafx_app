package main.java.pers.jiangyinzuo.rollcall.service;

import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;

/**
 * µãÃû
 * 
 * @author Jiang Yinzuo
 * 
 */
public interface RollCallService {
	public void insertRollCall(Student student, String presence, String rollcallType, TeachingClass teachingClass)
			throws IOException;

	boolean editRollCall();

	public List<RollCall> queryRollCallsByTeachingClassId(Integer teachingClassId)
			throws ClassNotFoundException, CustomException, IOException;
}
