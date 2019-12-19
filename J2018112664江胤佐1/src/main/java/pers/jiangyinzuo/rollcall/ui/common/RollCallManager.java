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
            if (!"�ѵ�".equals(r.getPresence())) {
                abnormalStudentList.add(r.getStudent());
            }
        }
        return abnormalStudentList;
    }

    /**
     * ��ȡ���ѧ������
     * @param count ��������
     * @param teachingClassStudentList ȫ���ѧ����
     * @return �����ѧ������
     */
    public static List<Student> getRandomStudent(int count, List<Student> teachingClassStudentList) {
        // �쳣����ֱ�ӷ���ȫ������
        if (count < 0 || count > teachingClassStudentList.size()) {
            return teachingClassStudentList;
        }
        List<Student> resultList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        // �ü�����Ϊ��ʱ�Ĵ洢���ݽṹ����ֹ���������ظ�
        while (set.size() < count) {
            set.add((int) (Math.random() * teachingClassStudentList.size()));
        }
        // ������ת��Ϊ�б�
        for (Integer i : set) {
            resultList.add(teachingClassStudentList.get(i));
        }
        return resultList;
    }
}
