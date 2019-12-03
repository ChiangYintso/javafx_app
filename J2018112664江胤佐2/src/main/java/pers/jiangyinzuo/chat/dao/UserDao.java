package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
public interface UserDao {
	/**
	 * 根据用户ID查找
	 * @param userId
	 * @return
	 */
	User queryUserByUserId(Long userId);
	
	/**
	 * 插入用户
	 * @param user 用户的实体类
	 * @return userId
	 */
	Long insertUser(User user);

	void updateUserByUserId(Integer userId, User user);

	void deleteUserByUserId(Integer userId);
}
