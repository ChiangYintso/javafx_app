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
	 */
	void insertRollCall(Long studentId, Long classId, String presence, Long rollCallType);

	/**
	 * 根据id修改rollCall
	 * @param rollCall
	 */
	void editRollCall(RollCall rollCall);

	/**
	 * 根据id删除rollCall
	 */
	void deleteRollCall(Long rollCallId);
	
	void bulkInsertRollCalls(List<RollCall> rollCallList);

	/**
	 * 根据教学班id查找点名记录
	 * @param teachingClassId 教学班id
	 * @return
	 */
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId);

	List<RollCall> queryRollCallsByStudentId(Long studentId);
}
