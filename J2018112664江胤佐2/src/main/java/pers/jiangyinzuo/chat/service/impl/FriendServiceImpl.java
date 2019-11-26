package pers.jiangyinzuo.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import pers.jiangyinzuo.chat.entity.User;
import pers.jiangyinzuo.chat.service.FriendService;

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
		User friend1 = new User(111, "Alice", "Hello World", null, new ArrayList<>());
		User friend2 = new User(222, "Bob", "Hello Java", null, new ArrayList<>());
		User friend3 = new User(555, "David", "Hello Python", null, new ArrayList<>());
		User friend4 = new User(333, "Eric", "Hello JavaScript", null, new ArrayList<>());
		User friend5 = new User(444, "Floyd", "Hello C++", null, new ArrayList<>());
		friendList.add(friend1);
		friendList.add(friend2);
		friendList.add(friend3);
		friendList.add(friend4);
		friendList.add(friend5);
		return friendList;
	}

}
