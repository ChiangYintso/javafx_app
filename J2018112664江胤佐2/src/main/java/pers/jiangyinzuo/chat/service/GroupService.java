package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.domain.entity.User;
import java.util.List;

public interface GroupService {
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
	
	List<Group> getGroupsByUserId(Integer userId);
}
