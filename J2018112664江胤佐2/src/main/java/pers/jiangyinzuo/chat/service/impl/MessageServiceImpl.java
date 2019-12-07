package pers.jiangyinzuo.chat.service.impl;

import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.dao.MessageDao;
import pers.jiangyinzuo.chat.dao.mysql.MessageDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.service.MessageService;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao = new MessageDaoImpl();
    /**
     * 将message存入数据库
     *
     * @param rawJson 发送给TcpServer的原始字节数组
     */
    @Override
    public void insertMessage(byte[] rawJson) {
        messageDao.insertMessage(Message.parseToMessageEntity(rawJson));
    }

    /**
     * 查询和好友聊天最近的5条记录
     *
     * @param friendId
     * @return
     */
    @Override
    public List<Message> queryRecentMessage(Long userId, Long friendId) {
        return messageDao.queryMessagesByUserId(userId, friendId, 0, 5);
    }
}
