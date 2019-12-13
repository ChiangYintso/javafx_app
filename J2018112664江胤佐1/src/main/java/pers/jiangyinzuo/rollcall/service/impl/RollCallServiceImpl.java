package pers.jiangyinzuo.rollcall.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private List<RollCall> teachingClassRollCallList;
	private TeachingClass teachingClass;

	public RollCallServiceImpl(TeachingClass teachingClass)
			throws CustomException {

		// ����ʵ����Dao
		this.rollCallDao = DaoFactory.createDao(RollCallDao.class);

		if (teachingClass == null) {
			throw new CustomException("δѡ��༶", false);
		}
		this.teachingClass = teachingClass;
//		this.teachingClassRollCallList = this.dao.queryRollCallsByTeachingClassId(teachingClass.getClassId());
	}

	@Override
	public void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, SQLException, ClassNotFoundException {

		this.teachingClassRollCallList = rollCallList;

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
	public void insertRollCall(Long studentId, Long classId, String presence, Integer rollCallType) {
		TeachingClass teachingClass = new TeachingClass.Builder().classId(classId).build();
		this.rollCallDao.insertRollCall(new RollCall.Builder().rollCallId(studentId)
				.teachingClass(teachingClass).rollCallType(rollCallType).build());
	}

	/**
	 * ����id�޸�rollCall
	 *
	 * @param rollCall
	 * @throws IOException
	 * @throws SQLException
	 */
	@Override
	public void editRollCall(RollCall rollCall) {
		this.rollCallDao.updateRollCall(rollCall);
	}


	@Override
	public void deleteRollCall(Long rollCallId) {

	}

//	@Override
//	public void editRollCall(RollCall originRollCall, RollCall rollCall) throws IOException, SQLException {
//		for (RollCall r : this.totalRollCallList) {
//			if (r.equals(originRollCall)) {
//				r = rollCall;
//				break;
//			}
//		}
//
//		for (RollCall r : this.teachingClassRollCallList) {
//			if (r.equals(originRollCall)) {
//				r = rollCall;
//				break;
//			}
//		}
//
//		this.bulkWriteRollCalls(this.totalRollCallList);
//	}

//	@Override
//	public void delRollCall(RollCall originRollCall) throws IOException, SQLException {
//		if (totalRollCallList == null || totalRollCallList.size() == 0) {
//			return;
//		}
//
//		for (RollCall r : this.totalRollCallList) {
//			if (r.equals(originRollCall)) {
//				this.totalRollCallList.remove(originRollCall);
//				break;
//			}
//		}
//
//		for (RollCall r : this.teachingClassRollCallList) {
//			if (r.equals(originRollCall)) {
//				this.teachingClassRollCallList.remove(originRollCall);
//				break;
//			}
//		}
//
//		this.bulkWriteRollCalls(this.totalRollCallList);
//	}


	@Override
	public List<RollCall> queryRollCallsByStudentId(Long studentId) {
		List<RollCall> resultList = new ArrayList<>();
		for (RollCall rollCall : teachingClassRollCallList) {
			if (studentId.equals(rollCall.getStudent().getStudentId())) {
				resultList.add(rollCall);
			}
		}
		return resultList;
	}
}
