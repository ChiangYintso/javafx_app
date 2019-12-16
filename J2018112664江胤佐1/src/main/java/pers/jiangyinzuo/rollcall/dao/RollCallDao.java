package pers.jiangyinzuo.rollcall.dao;

import java.util.List;

import pers.jiangyinzuo.rollcall.domain.dto.StudentRollCallResultDTO;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;

/**
 * @author Jiang Yinzuo
 */
public interface RollCallDao {

	void insertRollCall(RollCall rollCall);

	void bulkInsertRollCalls(List<RollCall> rollCallList);
	
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId, int row, String presence);

	List<RollCall> queryRollCallsByStudentId(Long studentId);

	void updateRollCall(RollCall rollCall);

	void bulkUpdateRollCalls(List<RollCall> rollCallList);

	void deleteRollCall(Long rollCallId);

	List<Student> queryAbnormalRollCallsByTeachingClassId(Long classId);

	List<StudentRollCallResultDTO> queryRollCallStatistic(Long classId);
}
