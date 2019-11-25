package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.config.Config;
import main.java.pers.jiangyinzuo.rollcall.dao.RollCallDao;
import main.java.pers.jiangyinzuo.rollcall.dao.fileimpl.RollCallDaoFileImpl;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.RollCallService;

/**
 * @author Jiang Yinzuo
 */
public class RollCallServiceImpl implements RollCallService {

	private RollCallDao dao;
	private List<RollCall> totalRollCallList;
	private List<RollCall> teachingClassRollCallList;
	private TeachingClass teachingClass;

	public RollCallServiceImpl(TeachingClass teachingClass)
			throws ClassNotFoundException, CustomException, IOException {
		this.dao = new RollCallDaoFileImpl();
		if (teachingClass == null) {
			throw new CustomException("未选择班级", false);
		}
		this.teachingClass = teachingClass;
		this.totalRollCallList = this.dao.queryAllRollCalls();
		this.teachingClassRollCallList = this.dao.queryRollCallsByTeachingClassId(teachingClass.getClassId());
	}

	@Override
	public void insertRollCall(Student student, String presence, String rollcallType) throws IOException, SQLException {
		RollCall rollCall = new RollCall(1L, presence, rollcallType,
				Instant.now().plusMillis(TimeUnit.HOURS.toMillis(Config.TIME_ZONE)), this.teachingClass, student);
		this.dao.insertRollCall(rollCall);
//		this.totalRollCallList.add(rollCall);
		this.teachingClassRollCallList.add(rollCall);
	}

	@Override
	public List<RollCall> queryTeachingClassRollCalls() {
		return this.teachingClassRollCallList;
	}

	@Override
	public void bulkWriteRollCalls(List<RollCall> rollCallList) throws IOException, SQLException {

		this.totalRollCallList.removeAll(this.teachingClassRollCallList);
		this.totalRollCallList.addAll(rollCallList);
		this.teachingClassRollCallList = rollCallList;

		this.dao.bulkInsertRollCalls(rollCallList);
	}

	@Override
	public void editRollCall(RollCall originRollCall, RollCall rollCall) throws IOException, SQLException {
		if (totalRollCallList == null || totalRollCallList.size() == 0) {
			return;
		}

		for (RollCall r : this.totalRollCallList) {
			if (r.equals(originRollCall)) {
				r = rollCall;
				break;
			}
		}

		for (RollCall r : this.teachingClassRollCallList) {
			if (r.equals(originRollCall)) {
				r = rollCall;
				break;
			}
		}

		this.bulkWriteRollCalls(this.totalRollCallList);
	}

	@Override
	public void delRollCall(RollCall originRollCall) throws IOException, SQLException {
		if (totalRollCallList == null || totalRollCallList.size() == 0) {
			return;
		}

		for (RollCall r : this.totalRollCallList) {
			if (r.equals(originRollCall)) {
				this.totalRollCallList.remove(originRollCall);
				break;
			}
		}

		for (RollCall r : this.teachingClassRollCallList) {
			if (r.equals(originRollCall)) {
				this.teachingClassRollCallList.remove(originRollCall);
				break;
			}
		}

		this.bulkWriteRollCalls(this.totalRollCallList);
	}

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
		List<Student> studentList = this.teachingClass.getStudentList();
		if (count < 0 || count > studentList.size()) {
			return studentList;
		}
		List<Student> resultList = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		while (set.size() < count) {
			set.add((int) (Math.random() * studentList.size()));
		}
		for (Integer i : set) {
			resultList.add(studentList.get(i));
		}
		return resultList;
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
