package main.java.pers.jiangyinzuo.rollcall.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;

/**
 * @author Jiang Yinzuo
 */
public interface RollCallDao {

	void insertRollCall(RollCall rollCall) throws IOException, SQLException;

	void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, SQLException, ClassNotFoundException;
	
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId) throws ClassNotFoundException, CustomException, IOException;

	List<RollCall> queryRollCallsByStudentId(Long studentId);

	void updateRollCall(RollCall rollCall, Long rollCallId);

	void bulkUpdateRollCalls(Map<Long, RollCall> rollCallMap) throws IOException, ClassNotFoundException;
}
