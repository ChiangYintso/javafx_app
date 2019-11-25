package main.java.pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.RollCallDao;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import main.java.pers.jiangyinzuo.rollcall.helper.MySqlHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void bulkInsertRollCalls(List<RollCall> rollCallList, boolean add) throws IOException, SQLException {
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
    public List<RollCall> queryRollCallsByTeachingClassId(Long teachingClassId) throws ClassNotFoundException, CustomException, IOException {
        return null;
    }

    @Override
    public List<RollCall> queryAllRollCalls() throws ClassNotFoundException, IOException {
        return null;
    }
}
