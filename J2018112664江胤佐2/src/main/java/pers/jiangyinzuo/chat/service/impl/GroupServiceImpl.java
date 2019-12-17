package pers.jiangyinzuo.chat.service.impl;

import pers.jiangyinzuo.chat.dao.GroupDao;
import pers.jiangyinzuo.chat.dao.mysql.GroupDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.service.GroupService;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao = new GroupDaoImpl();

    @Override
    public List<Long> getUserIdsInGroup(Long groupId) {
        return groupDao.queryUserIdInGroup(groupId);
    }

    @Override
    public void foundGroup(Group group, Long masterId) {
        groupDao.addMember(groupDao.addGroup(group, masterId), masterId, 3L);
    }

    @Override
    public void changeMemberPrivilege(Long userId, Long privilege, Long groupId) {
        groupDao.updateMemberPrivilege(userId, privilege, groupId);
    }

    @Override
    public void removeGroupMember(Long userId, Long groupId) {
        groupDao.deleteGroupMember(userId, groupId);
    }

    @Override
    public Group queryGroup(Long groupId) {
        return groupDao.queryGroup(groupId);
    }

    @Override
    public void addMember(long groupId, long userId) {
        groupDao.addMember(groupId, userId);
    }

    @Override
    public void editGroup(Group group) {
        groupDao.updateGroup(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupDao.deleteGroup(groupId);
    }

    @Override
    public List<Group> queryAllGroups() {
        return groupDao.queryAllGroups();
    }
}
