package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Message;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface MessageDao {
    /**
     * 返回最近的count条聊天记录
     *
     * @param user1Id 用户1id
     * @param user2Id 用户2id
     * @return
     */
    List<Message> queryMessagesByUserId(Long user1Id, Long user2Id, Integer row, Integer offset);

    /**
     * 根据sendToId和messageType查询Message
     *
     * @param sendToId
     * @param messageType > 10: 查询群聊消息; < 10: 查询好友聊天消息
     * @param row
     * @param offset      偏移量
     * @return
     */
    List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset);

    void insertMessage(Message message);

    void deleteMessage(Message message);
}
