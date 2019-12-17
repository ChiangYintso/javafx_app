package pers.jiangyinzuo.chat.domain.repository;

import pers.jiangyinzuo.chat.domain.dto.GroupMemberDTO;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class GroupRepo {

    /**
     * �û���Ⱥ���б�
     */
    private List<Group> userGroupList = null;

    private List<GroupMemberDTO> groupMemberDTOList = null;

    /**
     * Ⱥ��ԱID��ʵ�����ӳ��
     */
    private Map<Long, User> memberMap = null;

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
        resultList = MySqlHelper.queryMany(Group.class, sql, userId);
        return resultList;
    }

    public Map<Long, User> getMemberMap(Long groupId, boolean update) {
        if (update || memberMap == null) {
            updateMemberMap(groupId);
        }
        return memberMap;
    }

    public List<GroupMemberDTO> getGroupMemberDTOList(Long groupId) {
        String sql = "SELECT chat_user_group_relation.user_privilege," +
                " chat_user.user_id, chat_user.user_name FROM chat_user_group_relation, chat_user WHERE chat_user_group_relation.group_id = ? " +
                "AND chat_user.user_id = chat_user_group_relation.user_id";
        return MySqlHelper.queryMany(GroupMemberDTO.class, sql, groupId);
    }

    public void updateMemberMap(Long groupId) {
        String sql = "SELECT chat_user.* FROM chat_user, chat_user_group_relation WHERE group_id = ? AND chat_user.user_id = chat_user_group_relation.user_id";
        Map<Long, User> resultMap = new HashMap<>(20);
        List<User> list = MySqlHelper.queryMany(User.class, sql, groupId);
        assert list != null;
        for (User user : list) {
            resultMap.put(user.getUserId(), user);
        }
        memberMap = resultMap;
    }
}
