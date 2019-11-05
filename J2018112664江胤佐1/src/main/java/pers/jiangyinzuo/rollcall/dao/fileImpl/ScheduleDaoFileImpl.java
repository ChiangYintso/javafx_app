package main.java.pers.jiangyinzuo.rollcall.dao.fileImpl;

import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.ScheduleDao;
import main.java.pers.jiangyinzuo.rollcall.entity.Schedule;
import main.java.pers.jiangyinzuo.rollcall.service.validator.ScheduleValidator;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class ScheduleDaoFileImpl implements ScheduleDao {

	private static final String FILE_NAME = "schedules.txt";
	
	@Override
	public void insertSchedule(Schedule schedule) throws IOException {
		AppFile.writeSerializableEntity(schedule, FILE_NAME);
	}

	@Override
	public List<Schedule> querySchedulesByScheduleId(Integer scheduleId)
			throws ClassNotFoundException, CustomException, IOException {
		return AppFile.readSerializableEntities(FILE_NAME, new ScheduleValidator(), scheduleId);
	}

}
