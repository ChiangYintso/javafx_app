package main.java.pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	public void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, ClassNotFoundException {
		FileHelper.<RollCall>bulkWriteSerializableEntities(FILE_NAME, rollCallList, true);
	}

	@Override
	public void bulkUpdateRollCalls(Map<Long, RollCall> rollCallMap) throws IOException, ClassNotFoundException {
		List<RollCall> allRollCallList = getAllRollCalls();
		for (Map.Entry<Long, RollCall> entry : rollCallMap.entrySet()) {
			for (RollCall rollCall : allRollCallList) {
				if (rollCall.getRollCallId().equals(entry.getKey())) {
					allRollCallList.remove(rollCall);
					allRollCallList.add(entry.getValue());
					break;
				}
			}
		}
		FileHelper.<RollCall>bulkWriteSerializableEntities(FILE_NAME, allRollCallList, false);
	}

	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId)
			throws ClassNotFoundException, CustomException, IOException {

		return FileHelper.<RollCall>readSerializableEntities(FILE_NAME, new RollCallTeachingClassIdValidator(),
				teachingClassId);
	}

	@Override
	public List<RollCall> queryRollCallsByStudentId(Long studentId) {
		return null;
	}

	@Override
	public void updateRollCall(RollCall rollCall, Long rollCallId) {

	}

	@Override
	public void bulkUpdateRollCalls(Map<Long, RollCall> rollCallMap) {

	}

	private List<RollCall> getAllRollCalls() throws ClassNotFoundException, IOException {
		return FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
	}
}
