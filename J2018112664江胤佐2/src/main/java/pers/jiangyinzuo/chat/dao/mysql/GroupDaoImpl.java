package pers.jiangyinzuo.chat.dao.mysql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.dao.GroupDao;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Long addGroup(Group group, Long masterId) {
        String sql = "INSERT INTO chat_group(group_name, group_intro, master_user_id)" +
                " VALUES (?, ?, ?)";
        try {
           return MySqlHelper.executeUpdateReturnPrimaryKey(sql, group.getGroupName(), group.getGroupIntro(), masterId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    @Override
    public void addMember(Long groupId, Long userId) {
        String sql = "INSERT INTO chat_user_group_relation(user_id, group_id) VALUES (?, ?)";
        MySqlHelper.executeUpdate(sql, userId, groupId);
    }

    @Override
    public void addMember(Long groupId, Long userId, Long privilege) {
        String sql = "INSERT INTO chat_user_group_relation(user_id, group_id, user_privilege) VALUES (?, ?, ?)";
        MySqlHelper.executeUpdate(sql, userId, groupId, privilege);
    }

    public static void main(String[] args) {
        GroupDao dao = new GroupDaoImpl();
        List<Long> longs = dao.queryUserIdInGroup(2L);
        for (Long i : longs) {
            System.out.println(i);
        }
    }
}
