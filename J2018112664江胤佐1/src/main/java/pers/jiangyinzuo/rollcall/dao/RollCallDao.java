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

	void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, SQLException, ClassNotFoundException;
	
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId) throws ClassNotFoundException, IOException, SQLException;

	List<RollCall> queryRollCallsByStudentId(Long studentId) throws IOException, ClassNotFoundException, SQLException;

	void updateRollCall(RollCall rollCall, Long rollCallId) throws IOException, ClassNotFoundException, SQLException;

	void bulkUpdateRollCalls(Map<Long, RollCall> rollCallMap) throws IOException, ClassNotFoundException, SQLException;
}
