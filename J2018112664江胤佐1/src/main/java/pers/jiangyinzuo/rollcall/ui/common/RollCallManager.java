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
            if (!"已到".equals(r.getPresence())) {
                abnormalStudentList.add(r.getStudent());
            }
        }
        return abnormalStudentList;
    }

    /**
     * 获取随机学生名单
     * @param count 名单数量
     * @param teachingClassStudentList 全体教学名单
     * @return 随机的学生名单
     */
    public static List<Student> getRandomStudent(int count, List<Student> teachingClassStudentList) {
        // 异常数据直接返回全体名单
        if (count < 0 || count > teachingClassStudentList.size()) {
            return teachingClassStudentList;
        }
        List<Student> resultList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        // 用集合作为临时的存储数据结构，防止名单出现重复
        while (set.size() < count) {
            set.add((int) (Math.random() * teachingClassStudentList.size()));
        }
        // 将集合转换为列表
        for (Integer i : set) {
            resultList.add(teachingClassStudentList.get(i));
        }
        return resultList;
    }
}
