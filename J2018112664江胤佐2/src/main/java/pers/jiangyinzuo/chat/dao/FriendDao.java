package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.User;
import java.util.List;
import java.util.Map;

public interface FriendDao {
	Map<String, List<User>> queryFriendCategoryMapByUserId(Integer userId);
	
	void deleteFriend(Integer userId, Integer friendId);
	
	void addFriend(Integer userId, Integer friendId);
}
