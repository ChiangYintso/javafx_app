package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.dao.RollCallDao;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.helper.MySqlHelper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class RollCallDaoMysqlImpl implements RollCallDao {
    @Override
    public void insertRollCall(RollCall rollCall) throws SQLException {
        String sql = "INSERT INTO rollcall_rollcall_record(`rollcall_type`, `rollcall_time`, `presence`, `class_id`, `student_id`)";
        MySqlHelper.executeUpdate(sql, rollCall.getRollCallType(), rollCall.getRollCallTime(), rollCall.getPresence(), rollCall.getTeachingClass().getClassId(), rollCall.getStudent().getStudentId());
    }

    @Override
    public void bulkInsertRollCalls(List<RollCall> rollCallList) throws IOException, SQLException {
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
        ResultSet resultSet = MySqlHelper.executeQuery(sql, teachingClassId);
        List<RollCall> results = new ArrayList<>();
        try {
            while (resultSet.next()) {

            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<RollCall> queryRollCallsByStudentId(Long studentId) {
        return null;
    }

    @Override
    public void updateRollCall(RollCall rollCall, Long rollCallId) {

    }

    @Override
    public void bulkUpdateRollCalls(Map<Long, RollCall> rollCallMap) {

    }

    @Override
    public Long getRecordCount() throws IOException, ClassNotFoundException {
        return null;
    }
}
