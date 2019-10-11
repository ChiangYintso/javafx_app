package main.java.dao;

import main.java.entity.RollCall;

public interface RollCallDao {
	RollCall queryRollCallByClassId(Integer classId);
	
	RollCall queryRollCallByScheduleId(Integer scheduleId);
	
	RollCall queryRollCallByStudentId(Integer studentId);
	
	boolean createRollCall(RollCall rollCall);
	
	boolean editRollCall(RollCall rollCall);
}
