package main.java.pers.jiangyinzuo.rollcall.service.Impl;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.config.Config;
import main.java.pers.jiangyinzuo.rollcall.dao.RollCallDao;
import main.java.pers.jiangyinzuo.rollcall.dao.fileImpl.RollCallDaoFileImpl;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.RollCallService;

public class RollCallServiceImpl implements RollCallService {

	private RollCallDao dao;

	public RollCallServiceImpl() {
		this.dao = new RollCallDaoFileImpl();
	}

	@Override
	public boolean editRollCall() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertRollCall(Student student, String presence, String rollcallType, TeachingClass teachingClass) throws IOException {
		RollCall rollCall = new RollCall(1, presence, rollcallType, Instant.now().plusMillis(TimeUnit.HOURS.toMillis(Config.TIME_ZONE)), teachingClass, student);
		dao.insertRollCall(rollCall);
	}
	
	@Override
	public List<RollCall> queryRollCallsByTeachingClassId(Integer teachingClassId) throws ClassNotFoundException, CustomException, IOException {
		return this.dao.queryRollCallsByTeachingClassId(teachingClassId);
	}

}
