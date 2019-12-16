package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.helper.MySqlHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class RollCallDaoMysqlImpl implements RollCallDao {
    @Override
    public void insertRollCall(RollCall rollCall) {
        String sql = "INSERT INTO rollcall_rollcall_record(`rollcall_type`, `rollcall_time`, `presence`, `class_id`, `student_id`)" +
                " VALUES (?, ?, ?, ?, ?)";
        MySqlHelper.executeUpdate(sql, rollCall.getRollCallTypeLong(), rollCall.getRollCallTime(), rollCall.getPresence(), rollCall.getTeachingClass().getClassId(), rollCall.getStudent().getStudentId());
    }

    @Override
    public void bulkInsertRollCalls(List<RollCall> rollCallList) {
        String sql = "INSERT INTO rollcall.rollcall_rollcall_record(rollcall_type, presence, class_id, student_id)" +
                " VALUES(?, ?, ?, ?)";
        List<List<Object>> parametersList = new ArrayList<>();
        for (RollCall rollCall : rollCallList) {
            List<Object> parameters = new ArrayList<>();
            parameters.add(rollCall.getRollCallTypeLong());
            parameters.add(rollCall.getPresence());
            parameters.add(rollCall.getTeachingClass().getClassId());
            parameters.add(rollCall.getStudent().getStudentId());
            parametersList.add(parameters);
        }
        MySqlHelper.bulkExecuteUpdate(sql, parametersList);
    }

    @Override
    public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId, int row, String presence) {
        if ("È«²¿".equals(presence)) {
            String sql = "SELECT * FROM rollcall_rollcall_record WHERE `class_id` = ? ORDER BY rollcall_time DESC LIMIT ?, 10";
            return MySqlHelper.queryMany(RollCall.class, sql, teachingClassId, row);
        } else {
            String sql = "SELECT * FROM rollcall_rollcall_record WHERE `class_id` = ? AND `presence` = ? ORDER BY rollcall_time DESC LIMIT ?, 10";
            return MySqlHelper.queryMany(RollCall.class, sql, teachingClassId, presence, row);
        }
    }

    @Override
    public List<RollCall> queryRollCallsByStudentId(Long studentId) {
        String sql = "SELECT * FROM rollcall_rollcall_record WHERE `student_id` = ?";
        return MySqlHelper.queryMany(RollCall.class, sql, studentId);
    }

    @Override
    public void updateRollCall(RollCall rollCall) {
        String sql = "UPDATE rollcall_rollcall_record SET rollcall_type = ?," +
                " presence = ? WHERE rollcall_id = ?";
        MySqlHelper.executeUpdate(sql, rollCall.getRollCallTypeLong(), rollCall.getPresence(), rollCall.getRollCallId());
    }

    @Override
    public void bulkUpdateRollCalls(List<RollCall> rollCallList) {
        String sql = "UPDATE rollcall_rollcall_record SET rollcall_type = ?," +
                " presence = ? WHERE rollcall_id = ?";
        List<List<Object>> parametersList = new ArrayList<>();
        for (RollCall rollCall : rollCallList) {
            List<Object> params = new ArrayList<>();
            params.add(rollCall.getRollCallId());
            params.add(rollCall);
            parametersList.add(params);
        }
        MySqlHelper.bulkExecuteUpdate(sql, parametersList);
    }

    @Override
    public void deleteRollCall(Long rollCallId) {
        String sql = "DELETE FROM rollcall.rollcall_rollcall_record WHERE rollcall_id = ?";
        MySqlHelper.executeUpdate(sql, rollCallId);
    }

    @Override
    public List<Student> queryAbnormalRollCallsByTeachingClassId(Long classId) {
        String sql = "SELECT DISTINCT rollcall.rollcall_student.* FROM rollcall.rollcall_rollcall_record, rollcall.rollcall_student" +
                " WHERE `class_id` = ? AND rollcall_type = 1 AND presence <> ? AND rollcall_student.student_id = rollcall_rollcall_record.student_id";
        return MySqlHelper.queryMany(Student.class, sql, classId, RollCall.PRESENCE);
    }
}
