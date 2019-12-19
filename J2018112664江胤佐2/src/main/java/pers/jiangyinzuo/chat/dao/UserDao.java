package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.User;

import java.util.List;

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

	void updateUser(User user);

    List<User> queryAllUsers();
}
