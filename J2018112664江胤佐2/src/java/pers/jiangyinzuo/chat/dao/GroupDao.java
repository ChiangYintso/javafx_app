package java.pers.jiangyinzuo.chat.dao;

import java.pers.jiangyinzuo.chat.entity.Group;
import java.util.List;

public interface GroupDao {
	List<Group> queryGroupsByUserId(Integer userId);
	
	void insertGroup(Integer groupId);
	
	void deleteGroup(Integer groupId);
	
	void updateGroupByGroupId(Integer groupId);
}
