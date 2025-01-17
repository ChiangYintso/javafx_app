package pers.jiangyinzuo.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import pers.jiangyinzuo.chat.common.CustomInfo;
import pers.jiangyinzuo.chat.dao.AccountDao;
import pers.jiangyinzuo.chat.dao.UserDao;
import pers.jiangyinzuo.chat.dao.mysql.AccountDaoImpl;
import pers.jiangyinzuo.chat.dao.mysql.UserDaoImpl;
import pers.jiangyinzuo.chat.domain.dto.LoginDTO;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.AccountService;

/**
 * @author Jiang Yinzuo
 */
public class AccountServiceImpl implements AccountService {
	private UserDao userDao = new UserDaoImpl();
	private AccountDao accountDao = new AccountDaoImpl();

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
		accountDao.login(new LoginDTO(userId));
		return user;
	}

	/**
	 * �һ�����
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public String retrievePassword(Long userId) {
		User user = userDao.queryUserByUserId(userId);
		if (user == null) {
			return null;
		} else {
			return user.getPassword();
		}
	}

	@Override
	public void updateUserInfo(User user) {
		userDao.updateUser(user);
	}

	@Override
	public List<LoginDTO> queryLogsByUserId(Long userId) {
		return accountDao.queryLogByUserId(userId);
	}
}
