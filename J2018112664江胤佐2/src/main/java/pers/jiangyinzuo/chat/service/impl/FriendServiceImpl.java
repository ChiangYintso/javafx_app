package pers.jiangyinzuo.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.dao.FriendDao;
import pers.jiangyinzuo.chat.dao.UserDao;
import pers.jiangyinzuo.chat.dao.mysql.FriendDaoImpl;
import pers.jiangyinzuo.chat.dao.mysql.UserDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.FriendService;

/**
 * @author Jiang Yinzuo
 */
public class FriendServiceImpl implements FriendService {

	private UserDao userDao;

	private FriendDao friendDao = new FriendDaoImpl();

	public FriendServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public void addFriend(Long friendId) {
		friendDao.addFriend(UserState.getSingleton().getUser().getUserId(), friendId);
	}

	@Override
	public void delFriend(List<User> userList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getFriendInfo(User friend) {
		// TODO Auto-generated method stub

	}

	/**
	 * 通过userId查找用户
	 * @param userId 用户ID
	 */
	@Override
	public User searchUser(Long userId) {
		return userDao.queryUserByUserId(userId);
	}
}
