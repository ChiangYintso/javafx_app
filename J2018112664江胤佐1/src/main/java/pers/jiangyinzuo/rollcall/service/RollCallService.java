package pers.jiangyinzuo.rollcall.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;

/**
 * ����
 * 
 * @author Jiang Yinzuo
 * 
 */
public interface RollCallService {

	/**
	 * ����һ��rollCall
	 * @param student
	 * @param presence
	 * @param rollCallType
	 * @throws IOException
	 * @throws SQLException
	 */
	void insertRollCall(Student student, String presence, String rollCallType)
			throws IOException, SQLException;

	/**
	 * ����id�޸�rollCall
	 * @param rollCall
	 * @throws IOException
	 * @throws SQLException
	 */
	void editRollCall(RollCall rollCall) throws IOException, SQLException;

	/**
	 * ����idɾ��rollCall
	 * @param rollCall
	 * @throws IOException
	 * @throws SQLException
	 */
	void deleteRollCall(RollCall rollCall) throws IOException, SQLException;
	
	void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, SQLException, ClassNotFoundException;

	/**
	 * ���ݽ�ѧ��id���ҵ�����¼
	 * @param teachingClassId ��ѧ��id
	 * @return
	 */
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId);
	
	List<Student> getAbnormalStudent();
	
	List<Student> getRandomStudent(int count);

	List<RollCall> queryRollCallsByStudentId(Long studentId);
}
