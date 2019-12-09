package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.User;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public interface FriendDao {
	Map<String, List<User>> queryFriendCategoryMapByUserId(Integer userId);
	
	void deleteFriendship(Long userId, Long friendId);
	
	void addFriend(Long userId, Long friendId);

	/**
	 * 更新好友分组
	 * @param friendId 好友ID
	 * @param friendCategory
	 */
	void updateFriendCategory(Long userId, Long friendId, String friendCategory);
}
