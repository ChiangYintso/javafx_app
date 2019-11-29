package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;

public interface userStatusService {
	User login(Integer userId, String pwd);
	void logout(Integer userId);
}
