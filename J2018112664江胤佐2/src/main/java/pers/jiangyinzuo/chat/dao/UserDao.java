package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.User;

public interface UserDao {
	User queryUserByUserId(Integer userId);

	void insertUser(User user);

	void updateUserByUserId(Integer userId, User user);

	void deleteUserByUserId(Integer userId);
}
