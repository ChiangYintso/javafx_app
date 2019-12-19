package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public interface FriendService {
	void addFriend(Long userId);

	/**
	 * 删除好友
	 * @param friendId 好友ID
	 */
	void deleteFriend(Long friendId);

	/**
	 * 通过userId查找用户
	 * @param userId 用户ID
	 * @return 若成功找到, 返回User；否则返回null
	 */
	User searchUser(Long userId);

	void requestForFriendsStatus(Long userId);

	void updateFriendCategory(Long friendId, String friendCategory);
}
