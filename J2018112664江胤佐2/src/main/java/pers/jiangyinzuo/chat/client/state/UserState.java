package pers.jiangyinzuo.chat.client.state;

import pers.jiangyinzuo.chat.domain.entity.User;

/**
 * 单例模式, 存放用户状态
 * @author Jiang Yinzuo
 */
public class UserState {
	private User user;
	
	private static UserState singleton;
	
	private UserState() {};
	
	public static UserState getSingleton() {
		if (UserState.singleton == null) {
			UserState.singleton = new UserState();
		}
		return UserState.singleton;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
