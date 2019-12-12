package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class RollCallDaoFileImpl implements RollCallDao {

	private static final String FILE_NAME = "rollcalls.txt";

	@Override
	public void insertRollCall(RollCall rollCall) {
		try {
			FileHelper.writeSerializableEntity(rollCall, FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			throws ClassNotFoundException, IOException {
		 List<RollCall> rollCallList = FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
		 List<RollCall> results = new ArrayList<>();
		 for (RollCall rollCall : rollCallList) {
		 	if (teachingClassId.equals(rollCall.getTeachingClassId())) {
		 		results.add(rollCall);
			}
		 }
		 return results;
	}

	@Override
	public List<RollCall> queryRollCallsByStudentId(Long studentId) throws IOException, ClassNotFoundException {
		List<RollCall> rollCallList = FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
		List<RollCall> results = new ArrayList<>();
		for (RollCall rollCall : rollCallList) {
			if (studentId.equals(rollCall.getStudentId())) {
				results.add(rollCall);
			}
		}
		return results;
	}

	@Override
	public void updateRollCall(RollCall rollCall, Long rollCallId) throws IOException, ClassNotFoundException {
		List<RollCall> rollCallList = FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
		for (int i = 0; i < rollCallList.size(); ++i) {
			if (rollCallList.get(i).equals(rollCall)) {
				rollCallList.set(i, rollCall);
				return;
			}
		}
	}

	private List<RollCall> getAllRollCalls() throws ClassNotFoundException, IOException {
		return FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
	}
}
