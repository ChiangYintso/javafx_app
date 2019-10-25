package main.java.pers.jiangyinzuo.rollcall.dao;

import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Schedule;

public interface ScheduleDao {
	void insertSchedule(Schedule schedule) throws IOException;
	
	List<Schedule> querySchedulesByScheduleId(Integer studentId) throws ClassNotFoundException, CustomException, IOException;

}
