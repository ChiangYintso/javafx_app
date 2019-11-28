package pers.jiangyinzuo.rollcall.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;

/**
 * 点名
 * 
 * @author Jiang Yinzuo
 * 
 */
public interface RollCallService {

	/**
	 * 插入一条rollCall
	 * @param student
	 * @param presence
	 * @param rollCallType
	 * @throws IOException
	 * @throws SQLException
	 */
	void insertRollCall(Student student, String presence, String rollCallType)
			throws IOException, SQLException;

	/**
	 * 根据id修改rollCall
	 * @param rollCall
	 * @throws IOException
	 * @throws SQLException
	 */
	void editRollCall(RollCall rollCall) throws IOException, SQLException;

	/**
	 * 根据id删除rollCall
	 * @param rollCall
	 * @throws IOException
	 * @throws SQLException
	 */
	void deleteRollCall(RollCall rollCall) throws IOException, SQLException;
	
	void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, SQLException, ClassNotFoundException;

	/**
	 * 根据教学班id查找点名记录
	 * @param teachingClassId 教学班id
	 * @return
	 */
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId);
	
	List<Student> getAbnormalStudent();
	
	List<Student> getRandomStudent(int count);

	List<RollCall> queryRollCallsByStudentId(Long studentId);
}
