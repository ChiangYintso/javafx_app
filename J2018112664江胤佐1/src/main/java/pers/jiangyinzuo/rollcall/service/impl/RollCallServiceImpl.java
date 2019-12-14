package pers.jiangyinzuo.rollcall.service.impl;

import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.RollCallService;

/**
 * @author Jiang Yinzuo
 */
public class RollCallServiceImpl implements RollCallService {

	private RollCallDao rollCallDao;

	public RollCallServiceImpl() {

		// ����ʵ����Dao
		this.rollCallDao = DaoFactory.createDao(RollCallDao.class);
	}

	@Override
	public void bulkInsertRollCalls(List<RollCall> rollCallList) {
		this.rollCallDao.bulkInsertRollCalls(rollCallList);
	}

	/**
	 * ���ݽ�ѧ��id���ҵ�����¼
	 *
	 * @param teachingClassId ��ѧ��id
	 * @return
	 */
	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId) {
		return null;
	}


	/**
	 * ����һ��rollCall
	 *
	 * @param studentId
	 * @param classId
	 * @param presence
	 * @param rollCallType
	 */
	@Override
	public void insertRollCall(Long studentId, Long classId, String presence, Long rollCallType) {
		TeachingClass teachingClass = new TeachingClass.Builder().classId(classId).build();
		this.rollCallDao.insertRollCall(new RollCall.Builder().rollCallId(studentId)
				.teachingClass(teachingClass).rollCallType(rollCallType).build());
	}

	/**
	 * ����id�޸�rollCall
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
}
