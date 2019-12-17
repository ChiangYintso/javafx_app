package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.GroupDao;
import pers.jiangyinzuo.chat.domain.entity.Group;
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
        return MySqlHelper.queryMany(Long.class, sql, groupId);
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

    @Override
    public void updateMemberPrivilege(Long userId, Long privilege, Long groupId) {
        String sql = "UPDATE chat_user_group_relation SET user_privilege = ? WHERE user_id = ? AND group_id = ?";
        MySqlHelper.executeUpdate(sql, privilege, userId, groupId);
    }

    @Override
    public void deleteGroupMember(Long userId, Long groupId) {
        String sql = "DELETE FROM chat_user_group_relation WHERE user_id = ? AND group_id = ?";
        MySqlHelper.executeUpdate(sql, userId, groupId);
    }

    @Override
    public Group queryGroup(Long groupId) {
        String sql = "SELECT * FROM chat_group WHERE group_id = ?";
        return MySqlHelper.queryOne(Group.class, sql, groupId);
    }

    @Override
    public void updateGroup(Group group) {
        String sql = "UPDATE chat_group SET group_name = ? , group_intro = ?, is_blocked = ? WHERE group_id = ?";
        MySqlHelper.executeUpdate(sql, group.getGroupName(), group.getGroupIntro(), group.isBlocked(), group.getGroupId());
    }

    @Override
    public void deleteGroup(Long groupId) {
        String sql = "DELETE chat_group, chat_user_group_relation, chat_message FROM chat_group LEFT JOIN chat_user_group_relation  ON chat_user_group_relation.group_id = ? LEFT JOIN " +
                " chat_message ON chat_message.send_to = ? and chat_message.message_type > 10 " +
                " WHERE chat_group.group_id = ?";
        MySqlHelper.executeUpdate(sql, groupId, groupId, groupId);
    }

    @Override
    public List<Group> queryAllGroups() {
        String sql = "SELECT * FROM chat_group";
        return MySqlHelper.queryMany(Group.class, sql);
    }

    public static void main(String[] args) {
        GroupDao dao = new GroupDaoImpl();
        List<Long> longs = dao.queryUserIdInGroup(2L);
        for (Long i : longs) {
            System.out.println(i);
        }
    }
}
