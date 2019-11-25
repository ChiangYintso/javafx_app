package main.java.pers.jiangyinzuo.rollcall.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;

/**
 * @author Jiang Yinzuo
 */
public interface RollCallDao {

	void insertRollCall(RollCall rollCall) throws IOException, SQLException;

	void bulkInsertRollCalls(List<RollCall> rollCallList, boolean add) throws IOException, SQLException;
	
	List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId) throws ClassNotFoundException, CustomException, IOException;

	List<RollCall> queryAllRollCalls() throws ClassNotFoundException, IOException;
}
