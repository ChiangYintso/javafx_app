package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface FriendService {
	void addFriend(List<User> userList);
	
	void delFriend(List<User> userList);
	
	void getFriendInfo(User friend);

	/**
	 * 通过userId查找用户
	 * @param userId 用户ID
	 * @return 若成功找到, 返回User；否则返回null
	 */
	User searchUser(Long userId);
}
