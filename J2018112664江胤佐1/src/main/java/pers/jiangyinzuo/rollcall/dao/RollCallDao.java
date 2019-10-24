package main.java.pers.jiangyinzuo.rollcall.dao;

import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;

public interface RollCallDao {

	void insertRollCall(RollCall rollCall) throws IOException;

	void bulkInsertRollCalls(List<RollCall> rollCallList, boolean add) throws IOException;
	
	List<RollCall> queryRollCallsByTeachingClassId(Integer teachingClassId) throws ClassNotFoundException, CustomException, IOException;

	List<RollCall> queryAllRollCalls() throws ClassNotFoundException, IOException;
}
