package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Message;
import java.time.Instant;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface MessageDao {
	List<Message> queryMessagesByRoomId(Instant fromTime, Integer roomId);
	
	List<Message> queryMessagesByGroupId(Instant fromTime, Integer groupId);
	
	void insertMessage(Message message);
	
	void deleteMessage(Message message);
}
