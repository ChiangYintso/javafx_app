package pers.jiangyinzuo.rollcall.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.RollCallService;

/**
 * @author Jiang Yinzuo
 */
public class RollCallServiceImpl implements RollCallService {

	private RollCallDao dao;
	private List<RollCall> teachingClassRollCallList;
	private TeachingClass teachingClass;

	public RollCallServiceImpl(TeachingClass teachingClass)
			throws ClassNotFoundException, CustomException, IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

		// 反射实例化Dao
		this.dao = DaoFactory.createDao(RollCallDao.class);

		if (teachingClass == null) {
			throw new CustomException("未选择班级", false);
		}
		this.teachingClass = teachingClass;
//		this.teachingClassRollCallList = this.dao.queryRollCallsByTeachingClassId(teachingClass.getClassId());
	}

//	@Override
//	public void insertRollCall(RollCall rollCall) throws IOException, SQLException, ClassNotFoundException {
//		rollCall.setRollCallId(this.dao.getRecordCount() + 1);
//		this.dao.insertRollCall(rollCall);
//		this.teachingClassRollCallList.add(rollCall);
//	}

	@Override
	public void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, SQLException, ClassNotFoundException {

		this.teachingClassRollCallList = rollCallList;

		this.dao.bulkInsertRollCalls(rollCallList);
	}

	/**
	 * 根据教学班id查找点名记录
	 *
	 * @param teachingClassId 教学班id
	 * @return
	 */
	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId) {
		return null;
	}

	@Override
	public void insertRollCall(Student student, String presence, String rollCallType) throws IOException, SQLException {

	}

	/**
	 * 根据id修改rollCall
	 *
	 * @param rollCall
	 * @throws IOException
	 * @throws SQLException
	 */
	@Override
	public void editRollCall(RollCall rollCall) throws IOException, SQLException {

	}


	@Override
	public void deleteRollCall(RollCall rollCall) throws IOException, SQLException {

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
	public List<Student> getAbnormalStudent() {
		List<Student> abnormalStudentList = new ArrayList<>();
		for (RollCall r : this.teachingClassRollCallList) {
			if (!r.getPresence().equals("已到")) {
				abnormalStudentList.add(r.getStudent());
			}
		}
		return abnormalStudentList;
	}

	@Override
	public List<Student> getRandomStudent(int count) {
//		List<Student> studentList = this.teachingClass.getStudentList();
//		if (count < 0 || count > studentList.size()) {
//			return studentList;
//		}
//		List<Student> resultList = new ArrayList<>();
//		Set<Integer> set = new HashSet<>();
//		while (set.size() < count) {
//			set.add((int) (Math.random() * studentList.size()));
//		}
//		for (Integer i : set) {
//			resultList.add(studentList.get(i));
//		}
//		return resultList;
		return null;
	}

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
