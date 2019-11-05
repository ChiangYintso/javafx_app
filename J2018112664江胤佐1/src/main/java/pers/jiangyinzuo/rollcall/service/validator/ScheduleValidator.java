package main.java.pers.jiangyinzuo.rollcall.service.validator;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Schedule;

public class ScheduleValidator implements Validator {

	@Override
	public boolean validate(Class clazz, Object objFromFile, Object... obj) throws CustomException {
		Schedule schedule = (Schedule) objFromFile;
		Integer scheduleId = (Integer) obj[0];
		
		return schedule.getScheduleId().equals(scheduleId);
	}

	@Override
	public boolean validate(Object objFromFile, Object obj) {
		Schedule schedule = (Schedule) objFromFile;
		Integer scheduleId = (Integer) obj;
		
		return schedule.getScheduleId().equals(scheduleId);
	}

}
