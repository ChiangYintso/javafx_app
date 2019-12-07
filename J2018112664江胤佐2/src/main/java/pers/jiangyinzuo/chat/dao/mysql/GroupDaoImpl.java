package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.GroupDao;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class GroupDaoImpl implements GroupDao {
    @Override
    public List<Long> queryUserIdInGroup(Long groupId) {
        String sql = "SELECT user_id FROM chat_user_group_relation WHERE group_id = ?";
        try {
            return MySqlHelper.queryMany(Long.class, sql, groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        GroupDao dao = new GroupDaoImpl();
        List<Long> longs = dao.queryUserIdInGroup(2L);
        for (Long i : longs) {
            System.out.println(i);
        }
    }
}
