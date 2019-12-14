package pers.jiangyinzuo.rollcall.ui.common;

import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public class RollCallManager {
    public static List<Student> getAbnormalStudent(List<RollCall> rollCallList) {
        List<Student> abnormalStudentList = new ArrayList<>();
        for (RollCall r : rollCallList) {
            if (!r.getPresence().equals("ÒÑµ½")) {
                abnormalStudentList.add(r.getStudent());
            }
        }
        return abnormalStudentList;
    }

    private static List<Student> getRandomStudent(int count, List<Student> teachingClassStudentList) {
		if (count < 0 || count > teachingClassStudentList.size()) {
			return teachingClassStudentList;
		}
		List<Student> resultList = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		while (set.size() < count) {
			set.add((int) (Math.random() * teachingClassStudentList.size()));
		}
		for (Integer i : set) {
			resultList.add(teachingClassStudentList.get(i));
		}
		return resultList;
    }
}
