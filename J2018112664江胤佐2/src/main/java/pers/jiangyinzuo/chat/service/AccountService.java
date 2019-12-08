package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
public interface AccountService {
	/**
	 * 用户注册
	 * @param username 用户名
	 * @param password 密码
	 * @return 注册成功返回用户id, 失败返回-1L
	 */
	Long register(String username, String password);

	/**
	 * 用户登录
	 * @param userId 用户ID
	 * @param password 密码
	 * @return 登录成功返回User实体类, 失败null
	 */
	User login(Long userId, String password);

	/**
	 * 找回密码
	 * @param userId
	 * @return
	 */
	String retrievePassword(Long userId);

	void updateUserInfo(User user);
}
