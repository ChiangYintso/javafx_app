package java.pers.jiangyinzuo.chat.dao;

import java.pers.jiangyinzuo.chat.entity.User;
import java.util.List;

public interface UserDao {
	User queryUserByUserId(Integer userId);
	
	List<User> queryFriendsByUserId(Integer userId);

	void insertUser(User user);

	void updateUserByUserId(Integer userId, User user);

	void deleteUserByUserId(Integer userId);
}
