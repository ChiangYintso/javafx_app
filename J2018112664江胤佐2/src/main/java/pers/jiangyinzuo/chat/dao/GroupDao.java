package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Group;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface GroupDao {
	List<Long> queryUserIdInGroup(Long groupId);

	Long addGroup(Group group, Long masterId);

	void addMember(Long groupId, Long userId);
}
