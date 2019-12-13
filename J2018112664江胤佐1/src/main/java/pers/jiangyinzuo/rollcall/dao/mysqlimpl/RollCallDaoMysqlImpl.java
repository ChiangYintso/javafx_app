package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.helper.MySqlHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class RollCallDaoMysqlImpl implements RollCallDao {
    @Override
    public void insertRollCall(RollCall rollCall) {
        String sql = "INSERT INTO rollcall_rollcall_record(`rollcall_type`, `rollcall_time`, `presence`, `class_id`, `student_id`)";
        MySqlHelper.executeUpdate(sql, rollCall.getRollCallType(), rollCall.getRollCallTime(), rollCall.getPresence(), rollCall.getTeachingClass().getClassId(), rollCall.getStudent().getStudentId());
    }

    @Override
    public void bulkInsertRollCalls(List<RollCall> rollCallList) {
        String sql = "INSERT INTO rollcall_rollcall_record(`rollcall_type`, `rollcall_time`, `presence`, `class_id`, `student_id`)";
        List<List<Object>> parametersList = new ArrayList<>();
        for (RollCall rollCall : rollCallList) {
            List<Object> parameters = new ArrayList<>();
            parameters.add(rollCall.getRollCallType());
            parameters.add(rollCall.getRollCallTime());
            parameters.add(rollCall.getPresence());
            parameters.add(rollCall.getTeachingClass().getClassId());
            parameters.add(rollCall.getStudent().getStudentId());
            parametersList.add(parameters);
        }
        MySqlHelper.bulkExecuteUpdate(sql, parametersList);
    }

    @Override
    public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId) {
        String sql = "SELECT * FROM rollcall_rollcall_record WHERE `class_id` = ?";
        return MySqlHelper.queryMany(RollCall.class, sql, teachingClassId);
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
        MySqlHelper.executeUpdate(sql, rollCall.getRollCallType(), rollCall.getPresence(), rollCall.getRollCallId());
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
}
