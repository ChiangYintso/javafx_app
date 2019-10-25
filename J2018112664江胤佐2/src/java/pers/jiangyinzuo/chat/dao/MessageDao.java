package java.pers.jiangyinzuo.chat.dao;

import java.pers.jiangyinzuo.chat.entity.Message;
import java.time.Instant;
import java.util.List;

public interface MessageDao {
	List<Message> queryMessagesByRoomId(Instant fromTime, Integer roomId);
	
	List<Message> queryMessagesByGroupId(Instant fromTime, Integer groupId);
	
	void insertMessage(Message message);
	
	void deleteMessage(Message message);
}
