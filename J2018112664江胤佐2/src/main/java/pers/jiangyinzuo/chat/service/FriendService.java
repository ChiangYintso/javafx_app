package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public interface FriendService {
	void addFriend(Long userId);
	
	void delFriend(List<User> userList);
	
	void getFriendInfo(User friend);

	/**
	 * ͨ��userId�����û�
	 * @param userId �û�ID
	 * @return ���ɹ��ҵ�, ����User�����򷵻�null
	 */
	User searchUser(Long userId);

	void requestForFriendsStatus(Long userId);
}
