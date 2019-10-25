package main.java.pers.jiangyinzuo.chat.service;

import main.java.pers.jiangyinzuo.chat.entity.User;

public interface userStatusService {
	User login(Integer userId, String pwd);
	void logout(Integer userId);
}
