package pers.jiangyinzuo.chat.service.impl;

import pers.jiangyinzuo.chat.dao.GroupDao;
import pers.jiangyinzuo.chat.dao.mysql.GroupDaoImpl;
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
}
