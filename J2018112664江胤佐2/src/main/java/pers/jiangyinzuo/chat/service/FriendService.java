package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public interface FriendService {
	void addFriend(Long userId);

	/**
	 * ɾ������
	 * @param friendId ����ID
	 */
	void deleteFriend(Long friendId);

	/**
	 * ͨ��userId�����û�
	 * @param userId �û�ID
	 * @return ���ɹ��ҵ�, ����User�����򷵻�null
	 */
	User searchUser(Long userId);

	void requestForFriendsStatus(Long userId);

	void updateFriendCategory(Long friendId, String friendCategory);
}
