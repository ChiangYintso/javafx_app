package java.pers.jiangyinzuo.chat.service;

import java.pers.jiangyinzuo.chat.entity.Group;
import java.pers.jiangyinzuo.chat.entity.Message;
import java.pers.jiangyinzuo.chat.entity.User;
import java.util.List;

public interface groupService {
	Group registerGroup(String groupName, Integer userId);

	Group registerGroup(String groupName, Integer userId, List<User> userList);

	void dissolveGroup(Integer groupId);

	void addMember(Integer groupId, Integer userId);

	void removeMember(Integer groupId, Integer userId);

	void quitMember(Integer groupId, Integer userId);

	void setFounder(Integer groupId, Integer userId);

	void setManager(Integer groupId, Integer userId);

	void removeManager(Integer groupId, Integer userId);

	void upLoadFile();

	void sendMessage(Integer groupId, Message message);

	void withdrawMessage(Integer groupId, Message message);
}
