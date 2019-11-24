package main.java.pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.RollCallDao;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.service.validator.RollCallTeachingClassIdValidator;
import main.java.pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class RollCallDaoFileImpl implements RollCallDao {

	private static final String FILE_NAME = "rollcalls.txt";

	@Override
	public void insertRollCall(RollCall rollCall) throws IOException {
		FileHelper.writeSerializableEntity(rollCall, FILE_NAME);
	}

	@Override
	public void bulkInsertRollCalls(List<RollCall> rollCallList, boolean add) throws IOException {
		FileHelper.<RollCall>bulkInsertSerializableEntities(FILE_NAME, rollCallList, add);
	}

	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId)
			throws ClassNotFoundException, CustomException, IOException {

		return FileHelper.<RollCall>readSerializableEntities(FILE_NAME, new RollCallTeachingClassIdValidator(),
				teachingClassId);
	}

	@Override
	public List<RollCall> queryAllRollCalls() throws ClassNotFoundException, IOException {
		return FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
	}
}
