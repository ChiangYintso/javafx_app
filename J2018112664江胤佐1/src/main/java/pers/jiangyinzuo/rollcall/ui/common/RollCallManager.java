package pers.jiangyinzuo.rollcall.ui.common;

import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;

import java.util.*;

/**
 * @author Jiang Yinzuo
 */
public class RollCallManager {
	private static Map<Long, List<Student>> lastRollCallMap = new HashMap<>();

	public static void setLastRollCall(Long classId, List<Student> students) {
		lastRollCallMap.put(classId, students);
	}

	public static List<Student> getLastRollCall(Long classId) {
		return lastRollCallMap.get(classId);
	}

    public static List<Student> getAbnormalStudent(List<RollCall> rollCallList) {
        List<Student> abnormalStudentList = new ArrayList<>();
        for (RollCall r : rollCallList) {
            if (!"ÒÑµ½".equals(r.getPresence())) {
                abnormalStudentList.add(r.getStudent());
            }
        }
        return abnormalStudentList;
    }
}
