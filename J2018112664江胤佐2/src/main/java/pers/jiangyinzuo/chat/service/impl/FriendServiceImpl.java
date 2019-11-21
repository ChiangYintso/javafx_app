package main.java.pers.jiangyinzuo.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import main.java.pers.jiangyinzuo.chat.entity.User;
import main.java.pers.jiangyinzuo.chat.service.FriendService;

public class FriendServiceImpl implements FriendService {

	@Override
	public void addFriend(List<User> userList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delFriend(List<User> userList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFriendInfo(User friend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getFriendList(User user) {
		// TODO 将模拟数据切换成真实数据
		List<User> friendList = new ArrayList<>();
		User friend1 = new User(111, "Alice", "Hello World", new ArrayList<>());
		User friend2 = new User(222, "Bob", "Hello Java", new ArrayList<>());
		friendList.add(friend1);
		friendList.add(friend2);
		return friendList;
	}

}
