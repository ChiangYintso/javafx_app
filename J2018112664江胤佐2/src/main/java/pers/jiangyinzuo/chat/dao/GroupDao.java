package pers.jiangyinzuo.chat.dao;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface GroupDao {
	List<Long> queryUserIdInGroup(Long groupId);
}
