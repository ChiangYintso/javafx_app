package main.java.pers.jiangyinzuo.chat.service;

import main.java.pers.jiangyinzuo.chat.entity.User;
import java.util.List;

public interface friendService {
	void addFriend(List<User> userList);
	
	void delFriend(List<User> userList);
	
	void getFriendInfo(User friend);
}
