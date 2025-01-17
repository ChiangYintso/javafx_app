package pers.jiangyinzuo.rollcall.service.impl;

import java.sql.Timestamp;
import java.util.List;

import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.domain.dto.StudentRollCallResultDTO;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.RollCallService;

/**
 * @author Jiang Yinzuo
 */
public class RollCallServiceImpl implements RollCallService {

	private RollCallDao rollCallDao;

	public RollCallServiceImpl() {

		// 反射实例化Dao
		this.rollCallDao = DaoFactory.createDao(RollCallDao.class);
	}

	@Override
	public void bulkInsertRollCalls(List<RollCall> rollCallList) {
		this.rollCallDao.bulkInsertRollCalls(rollCallList);
	}

	/**
	 * 根据教学班id查找点名记录
	 *
	 * @param teachingClassId 教学班id
	 * @return
	 */
	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId, int row, String presence) {
		return rollCallDao.queryRollCallsByTeachingClassId(teachingClassId, row, presence);
	}


	/**
	 * 插入一条rollCall
	 *
	 * @param studentId
	 * @param classId
	 * @param presence
	 * @param rollCallType
	 */
	@Override
	public void insertRollCall(Long studentId, Long classId, String presence, Long rollCallType) {
		TeachingClass teachingClass = new TeachingClass.Builder().classId(classId).build();
		this.rollCallDao.insertRollCall(new RollCall.Builder().student(
				new Student.Builder().studentId(studentId).build())
				.presence(presence)
				.teachingClass(teachingClass).rollCallType(rollCallType)
				.rollCallTime(new Timestamp(System.currentTimeMillis())).build());
	}

	/**
	 * 根据id修改rollCall
	 *
	 * @param rollCall
	 */
	@Override
	public void editRollCall(RollCall rollCall) {
		this.rollCallDao.updateRollCall(rollCall);
	}


	@Override
	public void deleteRollCall(Long rollCallId) {

	}

	@Override
	public List<RollCall> queryRollCallsByStudentId(Long studentId) {
		return rollCallDao.queryRollCallsByStudentId(studentId);
	}

	@Override
	public List<Student> queryAbnormalStudent(Long classId) {
		return rollCallDao.queryAbnormalRollCallsByTeachingClassId(classId);
	}

	@Override
	public List<StudentRollCallResultDTO> queryRollCallStatistic(Long classId) {
		return rollCallDao.queryRollCallStatistic(classId);
	}
}
