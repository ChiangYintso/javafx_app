package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.Group;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface GroupService {
	List<Long> getUserIdsInGroup(Long groupId);

	void foundGroup(Group group, Long masterId);

	void changeMemberPrivilege(Long userId, Long privilege, Long groupId);

	void removeGroupMember(Long userId, Long groupId);

	Group queryGroup(Long groupId);

	/**
	 * º”»∫
	 */
	void addMember(long groupId, long userId);
}
