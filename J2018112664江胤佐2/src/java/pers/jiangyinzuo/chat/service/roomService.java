package java.pers.jiangyinzuo.chat.service;

import java.pers.jiangyinzuo.chat.entity.Message;

public interface roomService {
	void sendMessage(Integer roomId, Message message);
	
	void withdrawMessage(Integer roomId, Message message);
}
