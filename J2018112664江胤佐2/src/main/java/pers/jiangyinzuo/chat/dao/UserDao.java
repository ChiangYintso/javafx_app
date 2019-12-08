package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
public interface UserDao {
	/**
	 * �����û�ID����
	 * @param userId
	 * @return
	 */
	User queryUserByUserId(Long userId);
	
	/**
	 * �����û�
	 * @param user �û���ʵ����
	 * @return userId
	 */
	Long insertUser(User user);

	void updateUser(User user);

	void deleteUserByUserId(Integer userId);
}
