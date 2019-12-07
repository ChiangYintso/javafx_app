package pers.jiangyinzuo.chat.service;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface GroupService {
	List<Long> getUserIdsInGroup(Long groupId);
}
