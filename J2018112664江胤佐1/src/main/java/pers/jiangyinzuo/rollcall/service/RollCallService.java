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
	 */
	void insertRollCall(Long studentId, Long classId, String presence, Long rollCallType);

	/**
	 * ����id�޸�rollCall
	 * @param rollCall
	 */
	void editRollCall(RollCall rollCall);

	/**
	 * ����idɾ��rollCall
	 */
	void deleteRollCall(Long rollCallId);
	
	void bulkInsertRollCalls(List<RollCall> rollCallList);

	/**
	 * ���ݽ�ѧ��id���ҵ�����¼
	 * @param teachingClassId ��ѧ��id
	 * @return
	 */
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId);

	List<RollCall> queryRollCallsByStudentId(Long studentId);
}
