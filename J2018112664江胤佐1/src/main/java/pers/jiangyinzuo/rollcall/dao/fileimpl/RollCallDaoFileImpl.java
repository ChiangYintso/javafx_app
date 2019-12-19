package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.domain.dto.StudentRollCallResultDTO;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class RollCallDaoFileImpl implements RollCallDao {

	private static final String FILE_NAME = "rollcalls.txt";

	@Override
	public void insertRollCall(RollCall rollCall) {
		FileHelper.writeSerializableEntity(rollCall, FILE_NAME);
	}

	@Override
	public void bulkInsertRollCalls(List<RollCall> rollCallList) {
		FileHelper.<RollCall>bulkWriteSerializableEntities(FILE_NAME, rollCallList, true);
	}

	@Override
	public void bulkUpdateRollCalls(List<RollCall> rollCallList) {
		List<RollCall> allRollCallList = getAllRollCalls();
		for (RollCall newRollCall : rollCallList) {
			for (RollCall rollCall : allRollCallList) {
				if (rollCall.getRollCallId().equals(newRollCall.getRollCallId())) {
					allRollCallList.remove(rollCall);
					allRollCallList.add(newRollCall);
					break;
				}
			}
		}
		FileHelper.<RollCall>bulkWriteSerializableEntities(FILE_NAME, allRollCallList, false);
	}

	@Override
	public void deleteRollCall(Long rollCallId) {
		List<RollCall> tempRollCallList = getAllRollCalls();
		tempRollCallList.removeIf(rollCall -> rollCall.getRollCallId().equals(rollCallId));
		FileHelper.bulkWriteSerializableEntities(FILE_NAME, tempRollCallList, false);
	}

	@Override
	public List<Student> queryAbnormalRollCallsByTeachingClassId(Long classId) {
		List<RollCall> tempRollCallList = getAllRollCalls();
		List<Student> students = new ArrayList<>();
		StudentDao studentDao = new StudentDaoFileImpl();
		for (RollCall rollCall : tempRollCallList) {
			if (rollCall.getTeachingClassId().equals(classId) &&
					!RollCall.PRESENCE.equals(rollCall.getPresence())) {
				students.add(studentDao.queryStudent(rollCall.getStudentId()));
			}
		}
		return students;
	}

	@Override
	public List<StudentRollCallResultDTO> queryRollCallStatistic(Long classId) {
		return null;
	}


	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId, int row, String presence) {
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
	public List<RollCall> queryRollCallsByStudentId(Long studentId) {
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
	public void updateRollCall(RollCall rollCall) {
		// 读取文件中所有的实体类，并临时存放到rollCallList中
		List<RollCall> rollCallList = FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
		for (int i = 0; i < rollCallList.size(); ++i) {
			// 遍历列表，找到符合条件的实体类并修改
			if (rollCallList.get(i).equals(rollCall)) {
				rollCallList.set(i, rollCall);
				break;
			}
		}

		// 将所有实体类重写覆盖写入文件
		FileHelper.bulkWriteSerializableEntities(FILE_NAME, rollCallList, false);
	}

	private List<RollCall> getAllRollCalls() {
		return FileHelper.<RollCall>readAllSerializableEntities(FILE_NAME);
	}
}
