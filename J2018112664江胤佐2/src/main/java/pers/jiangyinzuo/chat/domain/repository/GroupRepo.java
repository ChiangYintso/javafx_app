package pers.jiangyinzuo.chat.domain.repository;

import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class GroupRepo {

    /**
     * �û���Ⱥ���б�
     */
    private List<Group> userGroupList = null;

    /**
     * ��ȡ�û�Ⱥ������
     * @param userId �û�id
     * @param update �Ƿ�Ϊ���²���
     * @return Ⱥ���б�
     */
    public List<Group> getGroupListByUserId(Long userId, boolean update) {
        if (!update && userGroupList != null) {
            return userGroupList;
        }
        String sql = "SELECT chat_group.* FROM " +
                "chat_user_group_relation, chat_group " +
                "WHERE chat_user_group_relation.user_id = ? " +
                "AND chat_user_group_relation.group_id = chat_group.group_id";
        List<Group> resultList = null;
        try {
            resultList = MySqlHelper.queryMany(Group.class, sql, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
