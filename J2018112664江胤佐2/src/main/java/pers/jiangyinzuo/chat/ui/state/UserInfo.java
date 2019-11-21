package main.java.pers.jiangyinzuo.chat.ui.state;

import main.java.pers.jiangyinzuo.chat.entity.User;

public class UserInfo {
	private User user;
	
	private static UserInfo singleton;
	
	private UserInfo() {};
	
	public static UserInfo getSingleton() {
		if (UserInfo.singleton == null) {
			UserInfo.singleton = new UserInfo();
		}
		return UserInfo.singleton;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
