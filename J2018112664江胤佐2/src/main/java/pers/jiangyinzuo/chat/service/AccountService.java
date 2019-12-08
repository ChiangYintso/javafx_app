package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
public interface AccountService {
	/**
	 * �û�ע��
	 * @param username �û���
	 * @param password ����
	 * @return ע��ɹ������û�id, ʧ�ܷ���-1L
	 */
	Long register(String username, String password);

	/**
	 * �û���¼
	 * @param userId �û�ID
	 * @param password ����
	 * @return ��¼�ɹ�����Userʵ����, ʧ��null
	 */
	User login(Long userId, String password);

	/**
	 * �һ�����
	 * @param userId
	 * @return
	 */
	String retrievePassword(Long userId);

	void updateUserInfo(User user);
}
