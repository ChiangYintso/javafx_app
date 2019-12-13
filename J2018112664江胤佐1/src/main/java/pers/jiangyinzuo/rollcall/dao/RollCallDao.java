package pers.jiangyinzuo.rollcall.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pers.jiangyinzuo.rollcall.domain.entity.RollCall;

/**
 * @author Jiang Yinzuo
 */
public interface RollCallDao {

	void insertRollCall(RollCall rollCall);

	void bulkInsertRollCalls(List<RollCall> rollCallList);
	
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId);

	List<RollCall> queryRollCallsByStudentId(Long studentId);

	void updateRollCall(RollCall rollCall);

	void bulkUpdateRollCalls(List<RollCall> rollCallList);
}
