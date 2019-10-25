package java.pers.jiangyinzuo.chat.dao;

import java.pers.jiangyinzuo.chat.entity.User;
import java.util.List;
import java.util.Map;

public interface FriendDao {
	Map<String, List<User>> queryFriendCategoryMapByUserId(Integer userId);
	
	void deleteFriend(Integer userId, Integer friendId);
	
	void addFriend(Integer userId, Integer friendId);
}
