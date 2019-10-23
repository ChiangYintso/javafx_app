package main.java.pers.jiangyinzuo.rollcall.dao.fileImpl;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.RollCallDao;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.service.validator.RollCallTeachingClassIdValidator;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class RollCallDaoFileImpl implements RollCallDao {

	private static final String FILE_NAME = "rollCalls.txt";
	
	@Override
	public void insertRollCall(RollCall rollCall) throws IOException {
		AppFile.writeSerializableEntity(rollCall, FILE_NAME);
	}

	@Override
	public void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException {
		AppFile.<RollCall>bulkInsertSerializableEntities(FILE_NAME, rollCallList);
	}

	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Integer teachingClassId) throws ClassNotFoundException, CustomException, IOException {
		
		return AppFile.<RollCall>readSerializableEntities(FILE_NAME, new RollCallTeachingClassIdValidator(), teachingClassId);
	}
}
