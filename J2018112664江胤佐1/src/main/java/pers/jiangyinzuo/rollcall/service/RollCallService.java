package main.java.pers.jiangyinzuo.rollcall.service;

import java.io.IOException;
import java.sql.SQLException;
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
	void insertRollCall(Student student, String presence, String rollcallType)
			throws IOException, SQLException;
	
	void editRollCall(RollCall originRollCall, RollCall rollCall) throws IOException, SQLException;
	
	void delRollCall(RollCall originRollCall) throws IOException, SQLException;
	
	void bulkWriteRollCalls(List<RollCall> rollCallList) throws IOException, SQLException;
	
	List<RollCall> queryTeachingClassRollCalls();
	
	List<Student> getAbnormalStudent();
	
	List<Student> getRandomStudent(int count);

	List<RollCall> queryRollCallsByStudentId(Long studentId);
}
