package pers.jiangyinzuo.chat.service.impl;

import java.util.ArrayList;

import pers.jiangyinzuo.chat.common.CustomInfo;
import pers.jiangyinzuo.chat.dao.UserDao;
import pers.jiangyinzuo.chat.dao.mysql.UserDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.AccountService;

/**
 * @author Jiang Yinzuo
 */
public class AccountServiceImpl implements AccountService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public Long register(String username, String password) {
		return userDao.insertUser(new User.Builder()
				.userName(username)
				.password(password)
				.build());
	}

	@Override
	public User login(Long userId, String password) {
		User user = userDao.queryUserByUserId(userId);
		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
}
